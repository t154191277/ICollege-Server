import requests
import re
from urllib import parse
import warnings
import pymysql

class crawler():
	#正方系统登录url
	_loginUrl = "https://jxgl.bistu.edu.cn:8443/zfca/login"
	#教育服务中心Url
	_personalUrl =  "http://jxgl.bistu.edu.cn/portal.do"
	#教务系统Url
	_mainsystemUrl = "https://jxgl.bistu.edu.cn:8443/zfca"
	#
	_baseUrl = "http://jwgl.bistu.edu.cn"
	#登录信息字符串
	_infoStr = ""
	#Session
	_session = requests.Session()
	#学号
	_id = ""
	#dbconn
	_connection = None
	#headers
	_headers = {'User-Agent':'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.116 Safari/537.36'}
	#超时
	_timeout = 10
	#是否使用SSL证书
	_vertify = False
	#姓名
	_name = ""
	#功能模块代码
	_moduleCode = {'专业选课':'N121105','重修选课':'N121103','体育选课':'N121102','公共选修课':'N121110','辅修专业选课':'N121111','历年四六级成绩查询':'N121301',
	'四六级及考研辅导班网上报名':'N121303','个人信息':'N121501','辅修报名':'N121504','学生意见反馈':'N121506','学生选专业方向':'N121507','班级课表':'N121601',
	'教师个人课表查询':'N121602','学生个人课表':'N121603','学生考试查询':'N121604','培养计划':'N121607','教师查询':'N121611','学生选课情况查询':'N121615','补考安排查询':'N121620'
	,'毕业补考名单确认':'N121622','个人成绩查询':'N121623','培养计划详情':'N121625','个人替换成绩':'N121626','重修班报名':'N121701','教学任务查询':'N123113','查询总课表':'N123114'}
	#功能模块地址后缀
	_moduleSuffix = {'专业选课':'xsxk.aspx','重修选课':'xscxbm.aspx','体育选课':'xstyk.aspx','公共选修课':'xf_xsqxxxk.aspx','辅修专业选课':'xsxk_fxxk','历年四六级成绩查询':'xsdjkscx.aspx',
	'四六级及考研辅导班网上报名':'bmxmb.aspx','个人信息':'xsgrxx.aspx','辅修报名':'xsfxbm.aspx','学生意见反馈':'xsyjfk.aspx','学生选专业方向':'xsxzyfx.aspx','班级课表':'tjkbcx.aspx','学生课表':'xskb.aspx',
	'教师个人课表查询':'jstjkbcx.aspx','学生个人课表':'xskbcx.aspx','学生考试查询':'xskscx.aspx','培养计划':'pyjh.aspx','教室查询':'xxjsjy.aspx','学生选课情况查询':'xsxkqk.aspx','补考安排查询':'xsbkkscx.aspx'
	,'毕业补考名单确认':'xs_bybkmdqr.aspx','个人成绩查询':'xscj_gc.aspx','培养计划详情':'pyjhxq.aspx','个人替换成绩':'xs_kcthxxcx_wzdx.sapx','重修班报名':'bmxmb.aspx','教学任务查询':'jxrwcx.aspx','查询总课表':'kbcx_zkb.aspx'}
	#数据库配置
	_config =  {
          'host':'localhost',
          'port':3306,
          'user':'root',
          'password':'aabb5459600',
          'db':'bistu',
          'charset':'utf8',
          'cursorclass':pymysql.cursors.DictCursor,
          }

	def __init__(self):
		warnings.filterwarnings("ignore")
		self._connection = pymysql.connect(**self._config)
		print('init')

	def login(self,id,passwd):
		self._id = id
		#登录教育信息服务中心
		lt = self.getlt()
		data = {'username':id,'password':passwd,'lt':lt,'losetime':'30','_eventId':'submit','submit1':'%B5%C7%A1%A1%C2%BC'}
		self._session.post(self._loginUrl,data = data,timeout = self._timeout,verify = self._vertify)
		print("登录教育信息服务中心----------")
		#登录教务系统
		params = {'yhlx':'student','login':'0124831917021889672','url':'xs_main.aspx'}
		r = self._session.get(self._mainsystemUrl,timeout = self._timeout,headers = self._headers,params = params)
		print("登录教务系统----------")
		#名字储存
		html = r.text
		name = re.compile(r'id="xhxm">(.*?)同学').findall(html)
		self._name = name[0]
		print("名字储存----------"+name[0])
		#infostr储存
		str = re.compile(r'edu.cn/(.*?)/').findall(r.url)
		self._infoStr = str[0]
		print("登录信息字符串储存----------"+self._infoStr)

		self._connection = pymysql.connect(**self._config)
		try:
			timestamp = ''
			with self._connection.cursor() as cursor:
				sql = 'select * from user where id = (%s)'
				r = cursor.execute(sql, (id))
				self._connection.commit()
				if r == 0:
					sql = 'insert into user values (%s, %s, %s)'
					cursor.execute(sql, (id, passwd, timestamp));
					self._connection.commit()
				
		finally:
			self._connection.close()


	def getlt(self):
		res = self._session.get(url = self._loginUrl,headers = self._headers,verify = self._vertify,timeout = self._timeout)
		html = res.text
		lt = re.compile(r'name="lt" value="(.*?)"').findall(html)
		return lt[0]
		

	#专业选课内容获取
	def getmajoy(self):
		html,url = self.gethtmlandurl('学生选课情况查询')
		#<option selected="selected" value="2016-2017">2016-2017</option> <option selected="selected" value="1">1</option>
		result = re.compile(r'selected="selected".*?>(.*?)</option>[\s\S]*?selected="selected".*?>(.*?)</option>').search(html).groups()
		schoolyear = result[0]
		semester = result[1]
		#<td>选课课号</td><td>课程代码</td><td>课程名称</td><td>课程性质</td><td>是否选课</td><td>教师姓名</td><td>学分</td><td>周学时</td>
		#<td>上课时间</td><td>上课地点</td><td>教材</td><td>修读标记</td><td>授课计划上传次数</td><td>授课计划最近上传时间</td><td>授课计划上传文件名</td><td>授课计划下载</td>
		result = re.compile(r'</tr>[\s\S]*?<td>(.*?)</td><td>(.*?)</td><td>.*?>(.*?)</a>.*?<td>(.*?)</td><td>.*?">(.*?)</a></td><td>(.*?)</td>[\s\S]*?">(.*?)</span>[\s\S]*?<td>(.*?)</td>').findall(html)
		#print(html)
		#print(result)
		self._connection = pymysql.connect(**self._config)
		try:
			_courseId = None
			j = 1
			for i in range(len(result)):

				courseCode = result[i][0]
				courseId = result[i][1]

				# id = 学号-课程号-数字 
				# example 2014011338-0BH04926-1

				courseName = result[i][2]
				courseType = result[i][3]
				teacherName = result[i][4]
				courseCredit = result[i][5]
				courseTime = result[i][6]
				if courseTime.isspace():
					coutseTime = None
				place = result[i][7]
				if place.isspace():
					coutseTime = None
				with self._connection.cursor() as cursor:
					sql = 'insert into course values ( %s, %s, %s, %s, %s, %s, %s, %s)'
					cursor.execute(sql, (courseCode, courseId, courseName, courseType, teacherName, courseCredit, courseTime, place));
					self._connection.commit()

				#插入中间表student_course
				with self._connection.cursor() as cursor:
					sql = 'insert into student_course values ( %s, %s)'
					cursor.execute(sql, (self._id, courseCode));
					self._connection.commit()
		finally:
			self._connection.close()

	#http://jwgl.bistu.edu.cn/(mtsei5ndvcve55454f0k44n3)/xskb.aspx?xh=2014011338&xhxx=20140113382016-20171
	def getcourse(self):
		html,url = self.gethtmlandurl('专业选课')
		#print(url)
		result = re.compile(r'id="xnxq"><b>(.*?)</b>.*?<b>(.*?)</b>').search(html).groups()
		schoolyear = result[0]
		semester = result[1]
		url = self._baseUrl + "/" + self._infoStr + "/" + self._moduleSuffix['学生课表']
		params = {'xh':self._id,'xhxx':self._id+schoolyear+semester}
		r = self._session.get(url,headers = self._headers,params = params,timeout = self._timeout)
		tablehtml = r.text
		#print(tablehtml)
		#<td>冲突信息</td><td>课号</td><td>星期</td><td>起始节</td><td>结束节</td><td>单双周</td><td>课程信息</td>
		courses = re.compile(r'</tr>[\s\S]*?\)-(.*?)</td><td>(.*?)</td><td>(.*?)</td><td>(.*?)</td><td>(.*?)</td><td>(.*?)<br>(.*?)<br>(.*?)<br>(.*?)</td>').findall(tablehtml)
		#print(courses)

		self._connection = pymysql.connect(**self._config)
		try:
			_courseId = None
			j = 1
			for i in range(len(courses)):
				#courseinfo = 课程号courseid+课程信息号
				courseinfo = courses[i][0]
				result = re.compile(r'(.*?)-(.*?)').search(courseinfo).groups()
				courseId = result[0]
				# id = 学号-课程号-数字 
				# example 2014011338-0BH04926-1
				mixid = self._id + '-' + courseId
				if(_courseId != courseId):
					j = 1
				else:
					j = j + 1
				mixid =  mixid + '-' + str(j)
				_courseId = courseId
				#courseCode = 学年-学期-课程号-课程信息号
				#example 2016-2017-0BH04926-20071610-1
				courseCode = schoolyear + '-' + semester + '-' + courseinfo
				day = courses[i][1]
				start = courses[i][2]
				end = courses[i][3]
				single = courses[i][4]
				if single.isspace():
					single = None
				courseName = courses[i][5]
				courseTime = courses[i][6]
				teacher = courses[i][7]
				place = courses[i][8]
				with self._connection.cursor() as cursor:
					sql = 'insert into course values (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s)'
					cursor.execute(sql, (mixid, courseCode, day, int(start), int(end), single, courseName, courseTime, teacher, place));
					self._connection.commit()
		finally:
			self._connection.close()

	def getmakeupexam(self,schoolyear,semester):
		html,url = self.gethtmlandurl('补考安排查询')
		result = re.compile(r'__VIEWSTATE.*?value="(.*?)"').search(html).groups()
		__EVENTTARGET = 'xqd'
		__EVENTARGUMENT= ''
		__VIEWSTATE = result[0]
		data = {'__EVENTTARGET':__EVENTTARGET,'__EVENTARGUMENT':__EVENTARGUMENT,'__VIEWSTATE':__VIEWSTATE,'xnd':schoolyear,'xqd':semester}
		print(data)
		r = self._session.post(url,data = data,headers = self._headers,timeout = self._timeout)
		examhtml = r.text
		#<td height="26">选课课号</td><td>课程名称</td><td>姓名</td><td>考试时间</td><td>考试地点</td><td>座位号</td><td>考试形式</td>
		exams = re.compile(r'height="23">.*?\)-(.*?)-.*?</td><td>(.*?)</td><td>(.*?)</td><td>(.*?)</td><td>(.*?)</td><td>(.*?)</td><td>(.*?)').findall(examhtml)

		self._connection = pymysql.connect(**self._config)
		try:
			for i in range(len(exams)):
				#print(exams[i])
				courId = exams[i][0]
				courName = exams[i][1]
				stuname = exams[i][2]
				time = exams[i][3]
				onwhere = exams[i][4]
				num = exams[i][5]
				examtype = exams[i][6]
				print(courId)
				with self._connection.cursor() as cursor:
					sql = 'insert into makeup values (%s, %s, %s, %s, %s, %s, %s)'
					cursor.execute(sql, (self._id, courId, courName, time, onwhere , examtype, num));
					self._connection.commit()
		finally:
			self._connection.close()

	def getexam(self,schoolyear,semester):
		html,url = self.gethtmlandurl('学生考试查询')
		result = re.compile(r'__VIEWSTATE.*?value="(.*?)"').search(html).groups()
		__EVENTTARGET = 'xqd'
		__EVENTARGUMENT= ''
		__VIEWSTATE = result[0]
		data = {'__EVENTTARGET':__EVENTTARGET,'__EVENTARGUMENT':__EVENTARGUMENT,'__VIEWSTATE':__VIEWSTATE,'xnd':schoolyear,'xqd':semester}
		print(data)
		r = self._session.post(url,data = data,headers = self._headers,timeout = self._timeout)
		examhtml = r.text
		#<td>选课课号</td><td>课程名称</td><td>姓名</td><td>考试时间</td><td>考试地点</td><td>考试形式</td><td>座位号</td><td>校区</td>
		exams = re.compile(r'</tr>[\s\S]*?<td>(.*?)</td><td>(.*?)</td><td>(.*?)</td><td>(.*?)</td><td>(.*?)</td><td>(.*?)</td><td>(.*?)</td><td>(.*?)</td>').findall(examhtml)
		try:
			for i in range(len(exams)):
				#print(exams[i])
				courId = exams[i][0]
				corname = exams[i][1]
				stuname = exams[i][2]
				time = exams[i][3]
				onwhere = exams[i][4]
				examtype = exams[i][5]
				num = exams[i][6]
				campus = exams[i][7]
				with self._connection.cursor() as cursor:
					sql = 'insert into examtime values (%s, %s, %s, %s, %s, %s)'
					cursor.execute(sql, (self._id, courId, time , onwhere, examtype, num, campus));
					self._connection.commit()
		finally:
			self._connection.close()
			

	def getpersoninfo(self):
		html,url = self.gethtmlandurl("个人信息")
		#print(url)
		result = re.compile(r'id="lbl_sfzh">(.*?)<[\s\S]*?id="lbl_xy">(.*?)<[\s\S]*?id="lbl_zymc">(.*?)<[\s\S]*?id="lbl_xzb">(.*?)<').search(html).groups()
		#print(result)
		idcart = result[0]
		print(idcart)
		department = result[1]
		marjoy = result[2]
		_class = result[3]
		self._connection = pymysql.connect(**self._config)
		try:
			with self._connection.cursor() as cursor:
				sql = 'select * from student where stuId = (%s)'
				r = cursor.execute(sql, (self._id))
				self._connection.commit()
				if r == 0:
					sql = 'insert into student values (%s, %s, %s, %s, %s, %s)'
					cursor.execute(sql, (self._id, self._name, idcart, department, marjoy, _class));
					self._connection.commit()
		finally:
			self._connection.close()

	def gethtmlandurl(self,module):
		baseurl = self._baseUrl + "/" + self._infoStr + "/" + self._moduleSuffix[module]
		params = '?' + 'xh=' + self._id + '&xm=' + '%BB%C6%C7%D5%D1%F4' + '&gnmkdm=' + self._moduleCode[module]
		url = baseurl + params
		r = self._session.get(url,headers = self._headers,timeout = self._timeout)
		print(r.url)
		html = r.text
		return html,url

	def getctegrade(self):
		html,url = self.gethtmlandurl("历年四六级成绩查询")
		#print(url)
		#print(html)
		#<td>学年</td><td>学期</td><td>等级考试名称</td><td>准考证号</td><td>考试日期</td><td>成绩</td><td>听力成绩</td><td>阅读成绩</td><td>写作成绩</td><td>综合成绩</td>
		result = re.compile(r'</tr>[\s\S]*?<td>(.*?)</td><td>(.*?)</td><td>(.*?)</td><td>(.*?)</td><td>(.*?)</td><td>(.*?)</td><td>(.*?)</td><td>(.*?)</td><td>(.*?)</td><td>(.*?)').findall(html)

		self._connection = pymysql.connect(**self._config)
		try:	
			for i in range(len(result)):
				#print(result[i])
				schoolyear = result[i][0]
				semester = result[i][1]
				rate = result[i][2]
				ticketnum = result[i][3]
				date = result[i][4]
				if date == '&nbsp':
					date = None
				total = result[i][5]
				if total.isspace():
					total = None
				listening = result[i][6]
				if listening.isspace():
					listening = None
				reading = result[i][7]
				if reading.isspace():
					reading = None
				writing = result[i][8]
				if writing.isspace():
					writing = None
				zhonghe = result[i][9]
				if zhonghe.isspace():
					zhonghe = None
				with self._connection.cursor() as cursor:
					sql = 'insert into cte values (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)'
					cursor.execute(sql, (self._id, schoolyear, semester , rate, ticketnum, date, total, listening, reading, writing, zhonghe));
					self._connection.commit()
		finally:
			self._connection.close()

	def getexamgrade(self):
		html,url = self.gethtmlandurl('个人成绩查询')
		__VIEWSTATE = re.compile(r'__VIEWSTATE.*?value="(.*?)"').findall(html)
		Button2 = "%D4%DA%D0%A3%D1%A7%CF%B0%B3%C9%BC%A8%B2%E9%D1%AF"
		data = {'__VIEWSTATE':__VIEWSTATE,'Button2':Button2}
		r = self._session.post(url,data = data,timeout = self._timeout,headers = self._headers)
		r.encoding = 'gb2312'
		result = re.compile(r'id="xftj"><b>(.*?)</b>[\s\S]*?学年[\s\S]*?</tr>([\s\S]*?)</table>[\s\S]*?平均学分绩点：(.*?)</b>[\s\S]*?课程代码[\s\S]*?</tr>([\s\S]*?)</table>').search(r.text).groups()
		#学分统计
		#所选学分124.50；获得学分99；重修学分15
		total = result[0]
		#print(result[0])
		r = re.compile(r'所选学分(.*?)；获得学分(.*?)；重修学分(.*?)').search(total).groups()
		grade1 = r[0]
		grade2 = r[1]
		grade3 = r[2]
		#在校成绩
		#<td>学年</td><td>学期</td><td>课程代码</td><td>课程名称</td><td>课程性质</td><td>课程归属</td><td>学分</td><td>绩点</td><td>平时成绩</td>
		#<td>期中成绩</td><td>期末成绩</td><td>实验成绩</td><td>成绩</td><td>辅修标记</td><td>补考成绩</td><td>重修成绩</td><td>学院名称</td><td>备注</td><td>重修标记</td><td>课程英文名称</td>
		grade = result[1]
		graderesult = re.compile(r'tr[\s\S]*?<td>(.*?)</td><td>(.*?)</td><td>(.*?)</td><td>(.*?)</td><td>(.*?)</td><td>(.*?)</td><td>(.*?)</td><td>'+
			'(.*?)</td><td>(.*?)</td><td>(.*?)</td><td>(.*?)</td><td>(.*?)</td><td>(.*?)</td><td>(.*?)</td><td>(.*?)</td><td>(.*?)</td><td>(.*?)</td><td>(.*?)</td><td>(.*?)</td><td></td>').findall(grade)

		
		self._connection = pymysql.connect(**self._config)
		try:	
			for i in range(len(graderesult)):
				print(graderesult[i])
				schoolyear = graderesult[i][0]
				semester = graderesult[i][1]
				courseId = graderesult[i][2]
				coursename = graderesult[i][3]
				coursetype = graderesult[i][4]
				#coursebelong = graderesult[i][5]
				credit = graderesult[i][6].strip()
				point = graderesult[i][7].strip()
				peacegrade = graderesult[i][8].strip()
				if peacegrade == '&nbsp;':
					peacegrade = None
				midgrade = graderesult[i][9]
				if midgrade == '&nbsp;':
					midgrade = None
				finalgrade = graderesult[i][10]
				if finalgrade == '&nbsp;':
					finalgrade = None
				testgrade = graderesult[i][11]
				if testgrade == '&nbsp;':
					testgrade = None
				grade = graderesult[i][12]
				#print(grade)
				makeupgrade = graderesult[i][14]
				if makeupgrade == '&nbsp;':
					makeupgrade = None
				regrade = graderesult[i][15]
				if regrade == '&nbsp;':
					regrade = None
				department = graderesult[i][16]
				tip = graderesult[i][17]
				if tip == '&nbsp;':
					tip = None
				with self._connection.cursor() as cursor:
					sql = 'insert into grade values (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)'
					cursor.execute(sql, (self._id, schoolyear, semester, coursename, coursetype, credit, courseId, point, peacegrade, midgrade, finalgrade, testgrade, grade, makeupgrade, regrade, department, tip));
					self._connection.commit()
		finally:
			self._connection.close()
		#gpa

		gpa = result[2]
		#print(gpa)
		#未通过课程
		#<td>课程代码</td><td>课程名称</td><td>学分</td><td>课程性质</td><td>最高成绩值</td><td>课程归属</td>
		#disgrade = result[3]
		#disgrade = re.compile(r'tr[\s\S]*?<td>(.*?)</td><td>(.*?))</td><td>(.*?)</td><td>(.*?)</td><td>(.*?))</td><td>(.*?)</td>').findall(disgrade)


clawler = crawler()
clawler.login('2014011338','aabb5459600')
#clawler.getpersoninfo()
#clawler.getcourse()
#clawler.getmajoy()
clawler.getmakeupexam('2015-2016','2')
#clawler.getctegrade()
#clawler.getexamgrade()