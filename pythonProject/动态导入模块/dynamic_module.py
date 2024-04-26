
#使用__import__函数动态导入模块
# m1 = __import__('m1.moduleFunc')
#
# m1.moduleFunc.test()
# m1.moduleFunc.test2()


#将模块中的test2方法变成_test2

# from m1.moduleFunc import *
#
# test()
# _test2() #_test2()无法被调用

# Traceback (most recent call last):
#   File "E:\myproject\python\pythonProject\动态导入模块\dynamic_module.py", line 14, in <module>
#     _test2() #test2()无法被调用
#     ^^^^^^
# NameError: name '_test2' is not defined. Did you mean: 'test'?

# 原因：zhihu.py文件中定义了test2方法，
# 但是没有将其变成私有方法，所以在dynamic_module.py中调用_test2()时，
# Python解释器会认为_test2()是公有方法，而找不到这个方法。


# 解决方法：不适用import *导入模块，
# 而是使用from m1.moduleFunc import test, _test2

# 最终代码如下：
from m1.moduleFunc import test, _test2

test()
_test2()