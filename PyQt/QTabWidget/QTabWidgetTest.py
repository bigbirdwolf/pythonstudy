'''
QTabWidgetTest is a simple example of using QTabWidget in PyQt.
'''
import sys
from PyQt5.QtWidgets import QApplication, QTabWidget, QWidget, QVBoxLayout, QPushButton, QTabWidget

class TabWidget(QWidget):
    def __init__(self):
        super().__init__()
        self.initUI()

    def initUI(self):
        # Create a QTabWidget object
        self.tabWidget = QTabWidget()
        # Create three QWidget objects
        self.tab1 = QWidget()
        self.tab2 = QWidget()
        self.tab3 = QWidget()

        # Add the UI elements to the three tabs
        self.tab1UI()
        self.tab2UI()
        self.tab3UI()

        # Add the tabs to the QTabWidget object
        self.tabWidget.addTab(self.tab1, "Tab 1")
        self.tabWidget.addTab(self.tab2, "Tab 2")
        self.tabWidget.addTab(self.tab3, "Tab 3")

        # Set the layout of the main window
        vbox = QVBoxLayout()
        vbox.addWidget(self.tabWidget)
        self.setLayout(vbox)
        self.setWindowTitle("QTabWidget Example")

    def tab1UI(self):
        self.tab1.layout = QVBoxLayout()
        self.tab1.button = QPushButton("Button 1")
        self.tab1.layout.addWidget(self.tab1.button)
        self.tab1.setLayout(self.tab1.layout)

    def tab2UI(self):
        self.tab2.layout = QVBoxLayout()
        self.tab2.button = QPushButton("Button 2")
        self.tab2.layout.addWidget(self.tab2.button)
        self.tab2.setLayout(self.tab2.layout)

    def tab3UI(self):
        self.tab3.layout = QVBoxLayout()
        self.tab3.button = QPushButton("Button 3")
        self.tab3.layout.addWidget(self.tab3.button)
        self.tab3.setLayout(self.tab3.layout)


if __name__ == '__main__':
    app = QApplication(sys.argv)
    ex = TabWidget()
    ex.show()
    sys.exit(app.exec_())