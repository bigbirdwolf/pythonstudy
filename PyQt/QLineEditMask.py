'''

PyQt支持的掩码

| Mask Character |                           Meaning                            |
| :------------: | :----------------------------------------------------------: |
|      `A`       | character of the Letter category required, such as A-Z, a-z. |
|      `a`       | character of the Letter category permitted but not required. |
|      `N`       | character of the Letter or Number category required, such as A-Z, a-z, 0-9. |
|      `n`       | character of the Letter or Number category permitted but not required. |
|      `X`       |              Any non-blank character required.               |
|      `x`       |     Any non-blank character permitted but not required.      |
|      `9`       |     character of the Number category required, e.g 0-9.      |
|      `0`       | character of the Number category permitted but not required. |
|      `D`       | character of the Number category and larger than zero required, such as 1-9 |
|      `d`       | character of the Number category and larger than zero permitted but not required, such as 1-9. |
|      `#`       | character of the Number category, or plus/minus sign permitted but not required. |
|      `H`       |        Hexadecimal character required. A-F, a-f, 0-9.        |
|      `h`       |      Hexadecimal character permitted but not required.       |
|      `B`       |               Binary character required. 0-1.                |
|      `b`       |         Binary character permitted but not required.         |
| Meta Character |                           Meaning                            |
|      `>`       |     All following alphabetic characters are uppercased.      |
|      `<`       |     All following alphabetic characters are lowercased.      |
|      `!`       |                 Switch off case conversion.                  |
|      `;c`      | Terminates the input mask and sets the *blank* character to *c*. |
|   `[ ] { }`    |                          Reserved.                           |
|      `\`       | Use `\` to escape the special characters listed above to use them as separators. |

'''

from PyQt5.QtWidgets import QWidget, QLineEdit, QApplication,QPushButton, QVBoxLayout, QHBoxLayout, QFormLayout
from PyQt5.QtCore import Qt

class QLineEditMask(QWidget):
    def __init__(self):
        super().__init__()
        self.initUI()


    def initUI(self):
        self.setWindowTitle('masked QLineEdit')
        formLayout = QFormLayout()

        ipLineEdit = QLineEdit()
        macLineEdit = QLineEdit()
        dateLineEdit = QLineEdit()
        licenseLineEdit = QLineEdit()

        ipLineEdit.setPlaceholderText('IP Address')
        macLineEdit.setPlaceholderText('MAC Address')
        dateLineEdit.setPlaceholderText('Date (MM/DD/YYYY)')
        licenseLineEdit.setPlaceholderText('License Plate')

        ipLineEdit.setInputMask('000.000.000.000;_')
        macLineEdit.setInputMask('HH:HH:HH:HH:HH:HH;_')
        dateLineEdit.setInputMask('0000-00-00;_')
        licenseLineEdit.setInputMask('>AAAAA-AAAAA-AAAAA-AAAAA-AAAAA;_')

        formLayout.addRow('IP Address:', ipLineEdit)
        formLayout.addRow('MAC Address:', macLineEdit)
        formLayout.addRow('Date:', dateLineEdit)
        formLayout.addRow('License Plate:', licenseLineEdit)

        self.setLayout(formLayout)


if __name__ == '__main__':
    app = QApplication([])
    ex = QLineEditMask()
    ex.show()
    app.exec_()
