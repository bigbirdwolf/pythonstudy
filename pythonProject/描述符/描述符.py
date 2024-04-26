
class Typed:

    def __init__(self,key,expected_type):
        self.key = key
        self.expected_type = expected_type

    def __get__(self, instance, owner):
        print('get方法')
        print(instance)
        print(owner)
        return instance.__dict__[self.key]

    def __set__(self, instance, value):
        if not isinstance(value, self.expected_type):
            raise TypeError('value must be ' + self.expected_type)
        print('set方法')
        instance.__dict__[self.key] = value

    def __delete__ (self, instance):
        instance.__dict__.pop(self.key)


class People:
    name = Typed('name',str)
    age = Typed('age', int)

    def __init__(self, name, age, salary):
        self.name = name
        self.age = age
        self.salary = salary


p1 = People('wolf', 25, 5000)
print(p1.name)
# print(p1.__dict__)

# p1.name
# # print(p1.name)
# p1.name = 'Bob'
# print(p1.__dict__)