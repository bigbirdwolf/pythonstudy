# class Hand:
#     pass
#
# class Foot:
#     pass
#
# class Trunk:
#     pass
#
#
# class Head:
#     pass
#
# class Person:
#     def __init__(self,id_num,name):
#         self.id_num = id_num
#         self.name = name
#         self.hand = Hand()
#         self.foot = Foot()
#         self.trunk = Trunk
#         self.head = Head()
#
# p1 = Person(1,"John")

class School:
    def __init__(self,name,location):
        self.name = name
        self.location = location

class Course:
    def __init__(self,name,price,period,school):
        self.school = school
        self.name = name
        self.price = price
        self.period = period


s1 = School("python","北京校区")
s2 = School("java",'上海校区')
s3 = School("c",'东京校区')

msg = '''
1 python 北京校区
2 java 上海校区
3 c 东京校区
'''

while True:
    print(msg)
    menu = {
        "1": s1,
        "2": s2,
        "3": s3
    }
    choice = input("请输入您的选择：")
    school_obj = menu[choice]

    name = input("课程名>>:")
    price = input("课程费用>>:")
    period = input("课程周期>>:")

    new_course = Course(name, price,period,school_obj)
    print('课程【%s】属于【%s】学校' %(new_course.name,new_course.school.name))


继承