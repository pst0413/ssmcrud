package com.ssm.crud.test;


import com.ssm.crud.bean.Department;
import com.ssm.crud.bean.Employee;
import com.ssm.crud.dao.DepartmentMapper;
import javax.annotation.Resource;

import com.ssm.crud.dao.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

/**
 * 测试dao的工作
 * 推荐Spring的项目就可以使用Spring的单元测试，可以自动注入我们需要的组件
 * 1.导入SpringTest模块
 * 2.@ContextConfiguration指定spring配置文件的位置
 * 3.直接autowired要使用的组件即可
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MapperTest {
    @Autowired
    DepartmentMapper departmentMapper;
    @Autowired
  EmployeeMapper employeeMapper;

    @Autowired
    SqlSession sqlSession;

    /**
     * 测试DepartmentMapper
     */
    @Test
    public void testCRUD(){
        //1.创建SpringTOC容器
        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        //从容器中获取mapper
        System.out.println(departmentMapper);
      /* // 1.插入几个部门
        departmentMapper.insertSelective(new Department(1,"开发部"));
        departmentMapper.insertSelective(new Department(2,"测试部"));
        departmentMapper.insertSelective(new Department(4,"运维部"));
        //2、生成员工数据，测试员工插入
        employeeMapper.insertSelective(new Employee(2,"jerry","M","jerry@atguigu.com",2));
        //3、批量插入多个员工:批量，使用可以执行批量操作的sqlSession
        for(){
            employeeMapper.insertSelective(new Employee(2,"jerry","M","jerry@atguigu.com",2));
        }*/
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        for (int i = 0;i<100;i++){
            String uid = UUID.randomUUID().toString().substring(0,5)+i;
            mapper.insertSelective(new Employee(null,"uid","M",uid+"@atguigu.com",1));
        }
        System.out.println("批量完成");
    }
}
