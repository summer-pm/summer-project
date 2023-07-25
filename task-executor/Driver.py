import time
import psutil
from typing import List

${solution}

def READ_STRING():
    return input()

def READ_INTEGER():
    return int(input())

def READ_STRING_ARR():
    input_str = input()
    if input_str == "[]":
        return []
    input_str = input_str.replace("[", "").replace("]", "").replace(" ", "")

    elements = input_str.split(",")

    arr = [str(element) for element in elements]
    return arr

def READ_INTEGER_ARR():
    input_str = input()
    if input_str == "[]":
        return []

    input_str = input_str.replace("[", "").replace("]", "").replace(" ", "")

    elements = input_str.split(",")

    arr = [int(element) for element in elements]
    return arr

if __name__ == '__main__':
${paramsInputSection}
    print(param1)
    print("just string")
    s = Solution()
    start_time = time.time()
    ret = Solution.${methodName}(s, ${paramList})
    end_time = time.time()
    process = psutil.Process()
    memory_info = memory_info = process.memory_info()
    used_memory_kb = memory_info.rss / (1024*1024)
    execution_time = (end_time - start_time) * 1e9
    print(ret)
    print(execution_time)
    print(used_memory_kb)

