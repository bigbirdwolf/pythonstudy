from PyQt5.QtCore import *
class Factorial(QObject):
    @pyqtSlot(int, result=int)
    def calculate(self, n):
        if n < 0:
            return None
        elif n == 0:
            return 1
        else:
            return n * self.calculate(n-1)
