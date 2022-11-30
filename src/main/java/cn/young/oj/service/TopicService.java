package cn.young.oj.service;

import cn.young.oj.entity.Topic;
import cn.young.oj.mapper.TopicMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;



@Service
public class TopicService {

    @Autowired
    private TopicMapper topicMapper;

    public List<Topic> selectAll(){
        return topicMapper.selectAll();
    }

    public Topic selectOne(Integer uid){
        return topicMapper.selectOne(uid);
    }
}
