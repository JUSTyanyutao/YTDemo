package com.just.ytdemo.util;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.JedisCommands;

/**
 * @author
 *
 * https://www.jianshu.com/p/a64df8dcfade
 *
 */
@Slf4j
@Service
public class CommonRedisService {


//    @Autowired
//    private RedisTemplate redisTemplate;
    
    @Resource(name = "redisTemplate")
    private StringRedisTemplate stringRedisTemplate;

    public void set(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    public void setExp(String key, String value, Long seconds) {
        stringRedisTemplate.opsForValue().set(key, value, seconds, TimeUnit.SECONDS);
    }

    public Long sadd(String key, String value) {
        return stringRedisTemplate.opsForSet().add(key, value);
    }

    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    public void setObject(String key, Object value) {
        stringRedisTemplate.opsForValue().set(key, (String) value);
    }

    public void setObjectExp(String key, Object value, long timeout, TimeUnit unit) {
        stringRedisTemplate.opsForValue().set(key, (String) value, timeout, unit);
    }

    public Object getObject(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    public boolean expire(String key, long timeout) {
        return stringRedisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }

    public void delete(String key) {
        stringRedisTemplate.delete(key);
    }

    public String rpop(String key) {
        String data = stringRedisTemplate.opsForList().rightPop(key);
        if (null == data || key.equals(data) || data.contains("[object Undefined]")) {
            return null;
        }
        return data;
    }

    public List<String> range(String key, int begin, int end) {
        List<String> list = stringRedisTemplate.opsForList().range(key, begin, end);
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        return list;
    }

    public Long expSecond(String key) {
        return stringRedisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    public String getValueFromTable(String tableName, String keyName) {
        HashOperations<String, String, Object> opsForHash = stringRedisTemplate.opsForHash();
        Object value = opsForHash.get(tableName, keyName);
        if (null == value) {
            return null;
        } else {
            return String.valueOf(value);
        }
    }

    public void putValueIntoTable(String tableName, String keyName, String value) {
        HashOperations<String, String, Object> opsForHash = stringRedisTemplate.opsForHash();
        opsForHash.put(tableName, keyName, value);
    }

    public void deleteValueFromTable(String tableName, String keyName) {
        HashOperations<String, String, Object> opsForHash = stringRedisTemplate.opsForHash();
        opsForHash.delete(tableName, keyName);
    }

    public Long incrementValueFromTable(String tableName, String keyName, long incrementNum) {
    	 HashOperations<String, String, Object> opsForHash = stringRedisTemplate.opsForHash();
    	 return opsForHash.increment(tableName, keyName, incrementNum);
    }
    
    public Set<Long> getSet(String key) {
        Set<Object> objSet = Collections.singleton(stringRedisTemplate.opsForSet().members(key));
        Set<Long> longSet = new LinkedHashSet<>();
        if (!CollectionUtils.isEmpty(objSet)) {
            for (Object o : objSet) {
                longSet.add(Long.valueOf(o.toString()));
            }
        }
        return longSet;
    }

//    /**
//     * 往固定订阅点  发布消息
//     *
//     * @param object
//     */
//    public void pushMessageToSubscribe(Object object) {
//        redisTemplate.convertAndSend(RedisConstant.SUBSCRIBE_POINT, object);
//    }

    /**
     * 往订阅推送消息
     * @param subscribePoint
     * @param object
     */
    public void pushMessageToSubscribe(String subscribePoint, Object object) {
        stringRedisTemplate.convertAndSend(subscribePoint, object);
    }
    
    /**
     * 分布式锁
     *
     * @param key
     * @param value
     * @param seconds
     * @return
     */
    public boolean setnx(final String key, final String value, Integer seconds) {
        long start = System.currentTimeMillis();
        try {
            while ((System.currentTimeMillis() - start) < seconds) {
                boolean flag = (Boolean) stringRedisTemplate.execute(new RedisCallback() {
                    @Override
                    public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
                        return redisConnection.setNX(getLockKey(key).getBytes(), value.getBytes());
                    }
                });
                if (flag) {
                    //暂设置为60s过期，防止异常中断锁未释放
                    stringRedisTemplate.expire(getLockKey(key), seconds, TimeUnit.SECONDS);
                    return true;
                }
                TimeUnit.SECONDS.sleep(3);
            }
        } catch (Exception e) {
            deleteNx(key);
            return false;
        }
        return false;
    }

    public void deleteNx(String key) {
        RedisConnection connection = stringRedisTemplate.getConnectionFactory().getConnection();
        connection.del(getLockKey(key).getBytes());
        connection.close();
    }

    private String getLockKey(String key) {
        return String.format("LOCK:%s", key);
    }


    public Boolean setIfAbsent(String lockName, String lockValue) {
        return stringRedisTemplate.opsForValue().setIfAbsent(lockName, lockValue);
    }

    public Boolean isExistSet(String key, Long wid) {
        return stringRedisTemplate.opsForSet().isMember(key, wid);
    }

    public Long getSetSize(String key) {
        return stringRedisTemplate.opsForSet().size(key);
    }

    public void putSet(String key, Long wid) {
        stringRedisTemplate.opsForSet().add(key, String.valueOf(wid));
    }

    public void deleteSet(String key, Long wid) {
        stringRedisTemplate.opsForSet().remove(key, wid);
    }

    public void batchPutSet(final String key, final Set<Long> wids) {
        stringRedisTemplate.executePipelined(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection redisConnection) throws DataAccessException {
                redisConnection.openPipeline();
                RedisSerializer<String> serializer = stringRedisTemplate.getStringSerializer();
                byte[] serKey = serializer.serialize(key);
                for (Long wid : wids) {
                    redisConnection.sAdd(serKey, serializer.serialize(String.valueOf(wid)));
                }
                redisConnection.closePipeline();
                return null;
            }
        });
    }

    public Long lpush(String key, String value) {
        return stringRedisTemplate.opsForList().leftPush(key, value);
    }

    public Long lpushAll(String key, List<String> values) {
        return stringRedisTemplate.opsForList().leftPushAll(key, values);
    }
    
    public Set<String> getKeys(String pattern) {
    	return stringRedisTemplate.keys(pattern);
    }

    public boolean hasKey(String key) {
    	return stringRedisTemplate.hasKey(key);
    }
    
    public long getListSize(String key) {
    	return stringRedisTemplate.opsForList().size(key);
    }
    
    /**
     * redis分布式锁，保证了原子性
     * @param key
     * @param value
     * @param expireTime 毫秒
     * @return
     */
    public boolean lock(final String key, final String value, final Long expireTime) {



    	try {
    		String result = stringRedisTemplate.execute(new RedisCallback<String>() {
    			@Override
    			public String doInRedis(RedisConnection connection) throws DataAccessException {
    				JedisCommands commons = (JedisCommands)connection.getNativeConnection();
    				return commons.set(key, value, "NX", "PX", expireTime);
    			}
    		}, true);
    		return "OK".equals(result) ? true : false;
		} catch (Exception e) {
			log.error("redis lock exception.", e);
		}
    	return false;
    }
    
    //TODO:需保证原子性
    public void unlock(String key, String value) {
    	try {
    		String current = stringRedisTemplate.opsForValue().get(key);
    		if (current != null && current.equals(value)) {
    			stringRedisTemplate.delete(key);
    		}
		} catch (Exception e) {
			log.error("redis unlock exception.", e);
		}
    }
    
    /*
    //ERR unknown command ' EVAL 
    public boolean  unlock(String key, String value) {
    	final String luaScript = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
    	final List<String> keys = new ArrayList<>();
    	keys.add(key);
    	final List<String> args = new ArrayList<>();
    	args.add(value);
    	try {
    		Long result = stringRedisTemplate.execute(new RedisCallback<Long>() {
    			@Override
    			public Long doInRedis(RedisConnection connection) throws DataAccessException {
    				Object nativeCon = connection.getNativeConnection();
    				//集群
    				if (nativeCon instanceof JedisCluster) {
    					log.info("JedisCluster unlock.");
    					return (Long) ((JedisCluster) nativeCon).eval(luaScript, keys, args);
    				}
    				//单机
    				else if (nativeCon instanceof Jedis) {
    					log.info("Jedis unlock.");
    					return (Long) ((Jedis) nativeCon).eval(luaScript, keys, args);
    				}
    				return 0L;
    			}
    		});
			return result != null && result > 0;
		} catch (Exception e) {
			log.error("redis unlock exception.", e);
		}
    	return false;
    }*/
}
