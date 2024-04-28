import sys
from PyQt5.QtWidgets import QApplication, QPushButton


class Window(QPushButton):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("QPushButton Pressed")
        self.setGeometry(500, 200, 400, 300)
        self.setText("Click me")
        self.pressed.connect(self.button_pressed)

    def button_pressed(self):
        print("Button clicked")

if __name__ == "__main__":
    app = QApplication(sys.argv)
    window = Window()
    window.show()
    sys.exit(app.exec_())