
# 面向对象编程（Object-Oriented Programming，OOP）是一种编程范式，它将程序的运行过程分解为一系列对象之间的交互。

def school(name,address,level):

    def get_name(school):
        print(school['name'])

    def get_address(school):
        print(school['address'])

    def get_level(school):
        print(school['level'])

    sch = {
        'name':name,
        'address':address,
        'level':level,
        'get_name':get_name,
        'get_address':get_address,
        'get_level':get_level
    }


    return sch


school1 = school('清华大学','北京市海淀区','本科')
print(school1)
school1['get_name'](school1)
print(school1['address'])
print(school1['level'])