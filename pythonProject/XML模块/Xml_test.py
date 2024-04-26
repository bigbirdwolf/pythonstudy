import xml.etree.ElementTree as ET

tree = ET.parse('country_data.xml')
root = tree.getroot()

# print(root.tag)
#
# for child in root:
#     print(child.tag,child.attrib)
#
# print(root[0][0].text)
#
# for neighbor in root.iter('neighbor'):
#     print(neighbor.attrib['name'])

# for rank in root.iter('rank'):
#     new_rank = int(rank.text) + 1
#     rank.text = str(new_rank)
#     rank.set('updated','yes')
#
# tree.write('country_data.xml')


for country in root.findall('country'):
     rank = int(country.find('rank').text)
     if rank > 50:
         root.remove(country)

tree.write('country_data.xml')