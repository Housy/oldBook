package com.housy.o2o.cache;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolWriper {
	/**
	 * Redis连接池对象
	 */
	private JedisPool jedisPool;
	/**
	 * 
	 * @param poolConfig 连接池配置好的相关信息
	 * @param host
	 * @param port redis服务端口
	 */
	public JedisPoolWriper(final JedisPoolConfig poolConfig,
			final String host, final int port) {
		try {
			jedisPool = new JedisPool(poolConfig, host, port);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	/**
	 * 获取Redis连接池对象
	 * 
	 */
	public JedisPool getJedisPool() {
		return jedisPool;
	}
	/**
	 * 注入Redis连接池对象
	 * @param jedisPool
	 */
	public void setJedisPool(JedisPool jedisPool) {
		// TODO Auto-generated method stub
		this.jedisPool = jedisPool;
	}
	
}
