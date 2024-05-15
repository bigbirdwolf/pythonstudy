'''

pyQt5中使用QWebEngineView加载网页，并调用JavaScript函数。

'''
import os
import sys

from PyQt5.QtCore import QUrl
from PyQt5.QtWidgets import QApplication, QMainWindow, QVBoxLayout, QWidget, QPushButton
from PyQt5.QtWebEngineWidgets import QWebEngineView
from PyQt5.QtGui import *

class QtCallJs(QWidget):
    def __init__(self):
        super().__init__()

        self.setWindowTitle('QtWebEngineView Demo')
        self.setGeometry(100, 100, 800, 600)
        self.layout = QVBoxLayout()
        self.setLayout(self.layout)
        # 创建一个 QWebEngineView 实例
        self.webview = QWebEngineView()
        url = os.getcwd() + '/login.html'
        self.webview.load(QUrl.fromLocalFile(url))  # 加载网页
        self.layout.addWidget(self.webview)

        button = QPushButton('调用JavaScript函数')
        button.clicked.connect(self.callJavaScript)
        self.layout.addWidget(button)


    def js_callback(self, value):
        print(value)
    def callJavaScript(self):
        self.value = 'Hello, PyQt5!'
        self.webview.page().runJavaScript('login("' + self.value + '")', self.js_callback)  # 调用JavaScript函数


if __name__ == '__main__':
    app = QApplication(sys.argv)
    window = QtCallJs()
    window.show()
    sys.exit(app.exec_())
