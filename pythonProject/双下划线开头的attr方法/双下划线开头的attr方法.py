# class Foo:
#     x = 1
#
#     def __init__(self,y):
#
#         self.y = y
#
#     def __getattr__(self, item):
#         print("执行__getattr__方法")
#
#
# f1 = Foo(2)
# print(f1.y)
# print(getattr(f1, 'y'))
# f1.sssssssss  #调用一个不存在的属性时，会执行__getattr__方法


# class Foo:
#     x = 1
#
#     def __init__(self,y):
#         self.y = y
#
#     def __delattr__(self, item):
#         print("删除操作__delattr__")
#
# f1 = Foo(2)
# del f1.y  #调用__delattr__方法
# del f1.x  #调用__delattr__方法


class Foo:
    x = 1

    def __init__(self,y):
        self.y = y

    def __setattr__(self, key,item):
        print("执行__setattr__方法")
        #self.key = item  #这种方法只要设置属性就会触发__setattr__方法,进入无线递归调用，导致栈溢出
        #  [Previous line repeated 992 more times]
        #   File "E:\myproject\python\pythonProject\双下划线开头的attr方法\双下划线开头的attr方法.py", line 39, in __setattr__
        #     print("执行__setattr__方法")
        # RecursionError: maximum recursion depth exceeded while calling a Python object
        self.__dict__[key] = item  #这种方法不会触发__setattr__方法，不会进入无线递归调用，不会导致栈溢出


f1 = Foo(2)
print(f1.__dict__)  #查看属性字典
f1.z = 3  #调用__setattr__方法
print(f1.__dict__)  #查看属性字典


# 总结：
# 1. __getattr__方法：当调用不存在的属性时，会触发__getattr__方法，可以返回属性的值或者执行其他操作。
# 2. __setattr__方法：当设置属性时，会触发__setattr__方法，可以对属性进行处理。
# 3. __delattr__方法：当删除属性时，会触发__delattr__方法，可以对属性进行处理。

# 4. 注意：不要在__setattr__方法中调用__setattr__方法，否则会导致无限递归。
