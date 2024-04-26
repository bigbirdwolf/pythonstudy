import json

# 定义一个字典
data = {
    'name': 'Alice',
    'age': 25,
    'city': 'Beijing'
}

# str_data = json.dumps(data)
# file = open('data.json', 'w')
# file.write(str_data)
#file.close()

file = open('data.json', 'r')
str_data = file.read()
json_data = json.loads(str_data)
print(json_data['name'])