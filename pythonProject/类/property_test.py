
class Person:
    def __init__(self, name, age,salary,workAge = 0):
        self.name = name
        self.age = age
        self.salary = salary
        self.workAge = workAge

    @property
    def mysalary(self):
        return self.salary * self.workAge


    @mysalary.setter
    def mysalary(self, value):
        self.workAge = value

    @mysalary.deleter
    def mysalary(self):
        self.workAge = 0


if __name__ == '__main__':

    p = Person("John", 25, 5000, 2)

    print(p.mysalary)
    p.mysalary = 5
    print(p.mysalary)
    del p.mysalary
    print(p.mysalary)