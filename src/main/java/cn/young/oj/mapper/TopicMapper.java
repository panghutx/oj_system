package cn.young.oj.mapper;

import cn.young.oj.entity.Topic;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TopicMapper {
    //新增题目
    public void insert(Topic topic);
    //查找题目列表：序号、难度、题目
    public List<Topic> selectAll();
    //查找题目详情页
    public Topic selectOne(Integer uid);

}
