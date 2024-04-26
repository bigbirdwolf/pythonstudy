import functools


class Lazyproperty:
    areaResult = 0

    def __init__(self, func):
        self.func = func

    def __get__(self,instance, owner):
        print("get")
        setattr(instance, self.func.__name__, self.func(instance))
        return self.func(instance)

class Room:

    areaResult = 0

    def __init__(self, name, width, height):
        self.name = name
        self.width = width
        self.height = height


    @Lazyproperty  #area=Typedroperty(area)
    def area(self):
        return self.width * self.height

    @Lazyproperty
    def test(self):
        print("test")


r1 = Room("living",100,100)
print(r1.area)
print(r1.area)
print(r1.area)
print(r1.area)
print(r1.area)
# r1.test
