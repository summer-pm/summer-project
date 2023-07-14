package ru.tinkoff.summer.taskexecutor;

<<<<<<< HEAD

import ru.tinkoff.summer.taskexecutor.domain.Attempt;

import ru.tinkoff.summer.taskexecutor.domain.executor.JavaExecutor;
import ru.tinkoff.summer.taskexecutor.domain.executor.LanguageExecutor;
import ru.tinkoff.summer.taskexecutor.domain.executor.PythonExecutor;
import ru.tinkoff.summer.taskexecutor.domain.task.Task;
import ru.tinkoff.summer.taskshareddomain.Language;
import ru.tinkoff.summer.taskshareddomain.Type;
import ru.tinkoff.summer.taskshareddomain.task.TaskParams;
import ru.tinkoff.summer.taskshareddomain.task.TaskTestCase;


import java.io.IOException;
import java.util.List;
import java.util.Set;

public class Main {
        public static void main(String[] args) throws IOException, InterruptedException {

            Task task = new Task();
            task.setTitle("Sum");
            task.setMethodName("sum");
            task.setParams(new TaskParams(List.of(Type.INTEGER, Type.INTEGER), Type.INTEGER));
            task.setTaskTestCases(
                    Set.of(
                            new TaskTestCase(List.of("-1", "1"), "0"),
                            new TaskTestCase(List.of("0", "0"), "0")));
            Attempt attempt =
                    new Attempt(
                            "class Solution {\n"
                                    + "    public int sum(int a, int b) {\n"
                                    + "return a + b;\n"
                                    + "    }\n"
                                    + "}",
                            Language.JAVA,
                            task,
                            null);
            LanguageExecutor javaEx = new JavaExecutor();

            Attempt pyAttempt =
                    new Attempt(
                            "class Solution(object):\n"
                                    + "    def sum(self, a, b):\n"
                                    + "        return a + b",
                            Language.PYTHON,
                            task,
                            null);
            LanguageExecutor pyEx = new PythonExecutor();
            System.out.println(task.getTitle());
            System.out.println("Python");
            System.out.println(pyEx.execute(pyAttempt));
            System.out.println("Java");
            System.out.println(javaEx.execute(attempt));


            Task task1 = new Task();
            task1.setTitle("TwoSum");
            task1.setMethodName("twoSum");
            task1.setParams(new TaskParams(
                    List.of(Type.INTEGER_ARR, Type.INTEGER), Type.INTEGER_ARR
            ));
            task1.setTaskTestCases(
                    Set.of(
                            new TaskTestCase(List.of("[3,2,4]", "6"), "[1,2]"),
                            new TaskTestCase(List.of("[2,7,11,15]", "9"), "[0,1]"),
                            new TaskTestCase(List.of("[3,3]", "6"), "[0,1]")));

            Attempt attempt1 = new Attempt(
                    "class Solution:\n" +
                            "    def twoSum(self, nums: List[int], target: int) -> List[int]:\n" +
                            "        hashmap = {}\n" +
                            "        for i in range(len(nums)):\n" +
                            "            hashmap[nums[i]] = i\n" +
                            "        for i in range(len(nums)):\n" +
                            "            complement = target - nums[i]\n" +
                            "            if complement in hashmap and hashmap[complement] != i:\n" +
                            "                return [i, hashmap[complement]] ",
                    Language.PYTHON, task1, null
            );
            Attempt attempt1Java = new Attempt(
                    "class Solution {\n" +
                            "    public int[] twoSum(int[] nums, int target) {\n" +
                            "        int[] res = new int[2];\n" +
                            "        int[] newNums = Arrays.copyOfRange(nums, 1, nums.length);\n" +
                            "        System.out.println(\"nums[0]: \" + nums[0]);\n" +
                            "        System.out.println(\"target: \" + target);\n" +
                            "        int newTarget = target-nums[0];\n" +
                            "        res[1] = search(newNums, newTarget) + 1;\n" +
                            "        if (res[1] != -1){\n" +
                            "            return res;\n" +
                            "        }\n" +
                            "        else{\n" +
                            "            res = twoSum(newNums, target);\n" +
                            "            res[0] = res[0] + 1;\n" +
                            "            res[1] = res[1] + 1;\n" +
                            "        }\n" +
                            "        return res;\n" +
                            "    }\n" +
                            "    public int search(int[] nums, int target){\n" +
                            "        System.out.println(\"search target: \" + target);\n" +
                            "        for (int i = 0; i < nums.length; i++){\n" +
                            "            if (nums[i]==target){\n" +
                            "                return i;\n" +
                            "            }\n" +
                            "        }\n" +
                            "        return -2;\n" +
                            "    }\n" +
                            "\n" +
                            "}",
                    Language.JAVA, task1, null
            );
            System.out.println(task1.getTitle());
            System.out.println("Python");
            System.out.println(pyEx.execute(attempt1));
            System.out.println("Java");
            System.out.println(javaEx.execute(attempt1Java));

        }
    }
=======
import ru.tinkoff.summer.taskexecutor.messaging.AttemptListener;

public class Main {

    public static void main(String[] args) {
        new AttemptListener();
    }

}
>>>>>>> abe64b06c86ae596b1dcb093b0d2d008782c59ce
