package cn.young.oj.controller;

import cn.young.oj.entity.Topic;
import cn.young.oj.mapper.TopicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class Test {


    @Autowired
    private TopicMapper topicMapper;


    public  String testInsert() {
        Topic topic = new Topic();
        topic.setTitle("两数之和");
        topic.setLevel("简单");
        topic.setDescription("给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。\n" +
                "\n" +
                "你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。\n" +
                "\n" +
                "你可以按任意顺序返回答案。\n" +
                "\n" +
                " \n" +
                "\n" +
                "示例 1：\n" +
                "\n" +
                "输入：nums = [2,7,11,15], target = 9\n" +
                "输出：[0,1]\n" +
                "解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。\n" +
                "示例 2：\n" +
                "\n" +
                "输入：nums = [3,2,4], target = 6\n" +
                "输出：[1,2]\n" +
                "示例 3：\n" +
                "\n" +
                "输入：nums = [3,3], target = 6\n" +
                "输出：[0,1]\n" +
                " \n" +
                "\n" +
                "提示：\n" +
                "\n" +
                "2 <= nums.length <= 104\n" +
                "-109 <= nums[i] <= 109\n" +
                "-109 <= target <= 109\n" +
                "只会存在一个有效答案\n" +
                "进阶：你可以想出一个时间复杂度小于 O(n2) 的算法吗？");
        topic.setTemplateCode("class Solution {\n" +
                "    public int[] twoSum(int[] nums, int target) {\n" +
                "\n" +
                "    }\n" +
                "}");
        topic.setTestCode("    public static void main(String[] args) {\n" +
                "        Solution solution = new Solution();\n" +
                "        // testcase1\n" +
                "        int[] nums = {2,7,11,15};\n" +
                "        int target = 9;\n" +
                "        int[] result = solution.twoSum(nums, target);\n" +
                "        if (result.length == 2 && result[0]+result[1]=target) {\n" +
                "            System.out.println(\"testcase1 OK\");\n" +
                "        } else {\n" +
                "            System.out.println(\"testcase1 failed!\");\n" +
                "        }\n" +
                "\n" +
                "        // testcase2\n" +
                "        int[] nums2 = {3,2,4};\n" +
                "        int target2 = 6;\n" +
                "        int[] result2 = solution.twoSum(nums2, target2);\n" +
                "        if (result2.length == 2 && result2[0]+result2[1]=target) {\n" +
                "            System.out.println(\"testcase2 OK\");\n" +
                "        } else {\n" +
                "            System.out.println(\"testcase2 failed!\");\n" +
                "        }\n" +
                "    }\n");
        topicMapper.insert(topic);
        return "ok";
    }

    @RequestMapping("/selectAll")
    @ResponseBody
    private List<Topic> selectAll(){
        List<Topic> topics = topicMapper.selectAll();
        return topics;
    }


    @RequestMapping("/selectOne")
    @ResponseBody
    private Topic selectOne(Integer uid){
        Topic topics = topicMapper.selectOne(uid);
        return topics;
    }

    public static void main(String[] args) {
        Test test = new Test();
        Topic topic = new Topic();
        topic.setTitle("两数相加");
        topic.setDescription("给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。\n" +
                "\n" +
                "请你将两个数相加，并以相同形式返回一个表示和的链表。\n" +
                "\n" +
                "你可以假设除了数字 0 之外，这两个数都不会以 0 开头。\n" +
                "\n" +
                " \n" +
                "\n" +
                "示例 1：\n" +
                "\n" +
                "\n" +
                "输入：l1 = [2,4,3], l2 = [5,6,4]\n" +
                "输出：[7,0,8]\n" +
                "解释：342 + 465 = 807.\n" +
                "示例 2：\n" +
                "\n" +
                "输入：l1 = [0], l2 = [0]\n" +
                "输出：[0]\n" +
                "示例 3：\n" +
                "\n" +
                "输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]\n" +
                "输出：[8,9,9,9,0,0,0,1]\n" +
                " \n" +
                "\n" +
                "提示：\n" +
                "\n" +
                "每个链表中的节点数在范围 [1, 100] 内\n" +
                "0 <= Node.val <= 9\n" +
                "题目数据保证列表表示的数字不含前导零");
        topic.setLevel("简单");
        topic.setTemplateCode("class Solution {\n" +
                "    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {\n" +
                "\n" +
                "    }\n" +
                "}");
        topic.setTestCode("");
        test.testInsert();
    }

}
