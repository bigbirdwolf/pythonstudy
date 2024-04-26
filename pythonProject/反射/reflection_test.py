class MyClass:
    def __init__(self, x):
        self.x = x

    def some_method(self):
        print("Hello from some_method")

obj = MyClass(10)

# 使用 hasattr() 检查属性是否存在
print(hasattr(obj, 'x'))  # 输出：True
print(hasattr(obj, 'y'))  # 输出：False

# 使用 getattr() 获取属性的值
print(getattr(obj, 'x'))  # 输出：10

# 使用 setattr() 设置属性的值
setattr(obj, 'y', 20)
print(obj.y)  # 输出：20
# 使用 hasattr() 检查方法是否存在
print(hasattr(obj, 'some_method'))  # 输出：True

# 使用 getattr() 获取方法并调用
method = getattr(obj, 'some_method')
method()  # 输出：Hello from some_method

# 使用 delattr() 删除属性
delattr(obj, 'y')
print(hasattr(obj, 'y'))  # 输出：False