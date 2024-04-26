#This is encapsulation test file.

class Car:
    def __init__(self, brand, model):
        self.__brand = brand  # 使用下划线作为前缀表示属性是受保护的
        self.__model = model

    def get_brand(self):
        return self.__brand

    def set_brand(self, brand):
        if type(brand) != str:
            raise ValueError("Brand must be a string")
        self.__brand = brand

# 创建对象并访问封装的属性和方法
car = Car("Toyota", "Prius")
print(car.get_brand())  # 输出：Toyota

car.set_brand("Honda")
print(car.get_brand())  # 输出：Honda