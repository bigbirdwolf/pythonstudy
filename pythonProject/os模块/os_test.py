import os


print(os.getcwd())

os.chdir("..")
print(os.getcwd())

# os.makedirs("test_dir/test_subdir")

os.removedirs("test_dir/test_subdir")