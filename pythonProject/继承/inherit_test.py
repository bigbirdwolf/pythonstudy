class Dad:
    money = 10000

    def __init__(self, name):
        self.name = name

    def hit_son(self):
        print('hit_son')


class Son(Dad):
    pass


Son.money = 20000

print(Son.money)  # 10000
print(Dad.money)


