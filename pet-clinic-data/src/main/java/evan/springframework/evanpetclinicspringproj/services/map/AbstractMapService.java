package evan.springframework.evanpetclinicspringproj.services.map;

import evan.springframework.evanpetclinicspringproj.model.BaseEntity;

import java.util.*;
// 这里我们规定T必须是extends BaseEntity的，ID必须是extends Long的
public abstract  class AbstractMapService <T extends BaseEntity,ID extends Long>{
    protected Map<Long,T> map = new HashMap<>();
    Set<T> findAll(){
        return new HashSet<>(map.values());
    }

    T findById(ID id){
        return map.get(id);
    }

    T  save(T object){
        if(object!=null){
            if(object.getId()==null){
                object.setId(getNextId());
            }
            map.put(object.getId(),object);
        } else{
            throw new RuntimeException("Object cannot be null");
        }

        return object;
    }

    void deleteById(ID id){
        map.remove(id);
    }

    void delete(T object){
        map.entrySet().removeIf(entry -> entry.getValue().equals(object));
    }

    // 因为hibernate和Spring data JPA都是可以自动帮我们take care ID的，
    // 那么我们现在用map的implementtation我们就用这个getNextId的method来模拟
    // 自动assign ID的过程
    private Long getNextId(){
        Long nextId = null;
        // 尝试让nextId等于目前map里最大的id+1
        try{
            nextId = Collections.max(map.keySet())+1;
                    // 如果报错了NoSuchElementException 说明现在map还一个id都没有，这样就让nextId=1L
            // 1L是1的Long datatype
        } catch (NoSuchElementException e){
            nextId = 1L;
        }
        return nextId;
    }

}
