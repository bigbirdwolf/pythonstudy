'''
QTreeViewTest.py

This is a simple example of using QTreeView widget in PyQt.

The QTreeView widget is used to display hierarchical data in a tree-like structure.
'''

import sys
from PyQt5.QtWidgets import QApplication, QTreeView, QTreeWidgetItem, QMainWindow, QAction, QMenu, QDirModel, \
    QAbstractItemView
from PyQt5.QtGui import QIcon

if __name__ == '__main__':
    app = QApplication(sys.argv)
    model = QDirModel()
    view = QTreeView()
    view.setModel(model)
    view.show()
    sys.exit(app.exec_())

