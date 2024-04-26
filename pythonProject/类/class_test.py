# class Person:
#     def __init__(self, name, age):
#         self.name = name
#         self.age = age
#
#     def say_hello(self):
#         print("Hello, my name is {} and I am {} years old.".format(self.name, self.age))
#
#
# person1 = Person("Alice", 25)
# print(Person.__dict__)
# person1.say_hello()

# 定义一个简单的类
class Dog:
    # 初始化方法
    def __init__(self, name, age):
        self.name = name
        self.age = age

    # 实例方法
    def bark(self):
        return "Woof!"


# 创建类的实例
my_dog = Dog("Buddy", 3)

# 使用类的实例
print(my_dog.name)  # 输出：Buddy
print(my_dog.age)  # 输出：3
print(my_dog.bark())  # 输出：Woof!

print(Dog.__name__) #类的名字(字符串)
print(Dog.__doc__) #类的文档字符串
print(Dog.__module__) #类的定义所在的模块(字符串)
print(Dog.__bases__) #类的所有父类构成元素的元组
print(Dog.__base__) #类的第一个父类
print(Dog.__dict__) #类的属性和方法的名字(字符串)的集合
print(Dog.__class__) #类的类(type)

print(my_dog.__dict__) #实例的属性和方法的名字(字符串)的集合
