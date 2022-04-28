package com.dlyy.api.common.jpa.repository;

import com.dlyy.api.common.jpa.entity.BaseEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity<ID>, ID extends Serializable> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

    /**
     * 根据对象属性查询对象信息列表
     *
     * @param propertyName 属性名称
     * @param value        属性值
     * @param sorts        排序对象
     * @return List<T>     满足查询条件的对象信息列表
     */
    List<T> findByProperty(String propertyName, Object value, Sort... sorts);

    /**
     * 根据对象属性查询唯一对象信息列表
     *
     * @param propertyName 属性名称
     * @param value        属性值
     * @param sorts        排序对象
     * @return List<T>     满足查询条件的对象信息列表
     */
    T findOneByProperty(String propertyName, Object value, Sort... sorts);

    /**
     * 根据JQL查询对象信息
     * @param jpql JQL查询语句
     * @param queryParams 查询参数
     * @return 数据库对象列表
     */
    List<T> findByJql(String jpql, Map<String, Object> queryParams);

    /**
     * 根据原生SQL查询数据库信息
     * @param sql SQL查询语句
     * @param queryParams 查询参数
     * @return 数据库信息列表
     */
    List<?> findBySql(String sql, Map<String, Object> queryParams);
}
