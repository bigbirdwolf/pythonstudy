import sys


from PyQt5.QtWidgets import (QApplication,
                             QWidget,
                             QMainWindow,
                             QLabel,
                             QHBoxLayout,
                             QPushButton)
def onClick_Button():
    print("1")
    print("widget.x() = %d" % window.x()) #窗口横坐标
    print("widget.h() = %d" % window.y()) #窗口纵坐标
    print("widget.width() = %d" % window.width()) #工作区宽度
    print("widget.height() = %d" % window.height()) #工作区高度

    print("2")
    print("widget.geometry().x() = %d" % window.geometry().x())
    print("widget.geometry().y() = %d #不包标签栏的坐标" % window.geometry().y())
    print("widget.geometry().width() = %d " % window.geometry().width())
    print("widget.geometry().height() = %d" % window.geometry().height())

    print("3")
    print("widget.frameGeometry().x() = %d" % window.frameGeometry().x())
    print("widget.frameGeometry().y = %d" % window.frameGeometry().y())
    print("widget.frameGeometry().width() = %d" % window.frameGeometry().width())
    print("widget.frameGeometry().height() = %d" % window.frameGeometry().height()) #含标签栏的高度

app = QApplication(sys.argv)

window = QWidget()
btn = QPushButton(window)
btn.setText("按钮")
btn.clicked.connect(onClick_Button)
btn.move(24,52)
window.resize(300,240) #设置工作区的尺寸
window.move(250,200)
window.setWindowTitle("屏幕坐标")

window.show()

sys.exit(app.exec_())

