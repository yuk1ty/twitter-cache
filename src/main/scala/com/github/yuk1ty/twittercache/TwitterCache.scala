package com.github.yuk1ty.twittercache

import com.github.benmanes.caffeine.cache.Cache
import com.twitter.cache.caffeine.CaffeineCache
import com.twitter.cache.guava.GuavaCache
import com.twitter.util.Future

/*
 * Copyright 2018 Yuki Toyoda
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

private[twittercache] trait GenericCache[K <: AnyRef, V] {

  /**
    * Execute function `f` if there is no cache in it.
    * If there are some cache, returns their values.
    */
  def applyIfNeeded(f: K => Future[V]): K => Future[V]
}

case class GuavaTwitterCache[K <: AnyRef, V](
    private val underlying: com.google.common.cache.Cache[K, Future[V]])
    extends GenericCache[K, V] {
  override def applyIfNeeded(f: K => Future[V]): K => Future[V] =
    GuavaCache.fromCache(f, underlying)
}

case class TwitterCache[K <: AnyRef, V](
    private val underlying: Cache[K, Future[V]])
    extends GenericCache[K, V] {
  override def applyIfNeeded(f: K => Future[V]): K => Future[V] =
    CaffeineCache.fromCache(f, underlying)
}

/**
  * Key for [[GenericCache]]
  *
  * @param key key value (like deriving [[AnyVal]])
  * @tparam K type of key
  */
case class TwitterCacheKey[K](private val key: K) {
  def unwrap: K = key
}
