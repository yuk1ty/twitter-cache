package com.github.yuk1ty.twittercache

import java.util.concurrent.TimeUnit

import com.github.benmanes.caffeine.cache.Caffeine
import com.google.common.cache.CacheBuilder
import com.twitter.util.{Await, Future}
import org.scalatest.{AsyncWordSpec, WordSpec}

/*
 * Copyright 2017 Yuki Toyoda
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

class TwitterCacheSpec extends WordSpec {

  "TwitterCache" should {
    "cache future value" in {
      val caffeine = Caffeine
        .newBuilder()
        .maximumSize(1)
        .expireAfterWrite(10, TimeUnit.SECONDS)
        .build[TwitterCacheKey[Long], Future[String]]
      val twitterCache = TwitterCache(caffeine)
      Await.result(
        twitterCache
          .applyIfNeeded(_ => Future.value("AAA"))(TwitterCacheKey(1))) == "AAA"
    }

    "with companion object" in {
      object Cache {
        def create[K <: AnyRef, V](
            maximumSize: Long,
            expireAfterAccessSeconds: Long): TwitterCache[K, V] = {
          lazy val caffeine = Caffeine
            .newBuilder()
            .maximumSize(maximumSize)
            .expireAfterAccess(expireAfterAccessSeconds, TimeUnit.SECONDS)
            .build[K, Future[V]]()
          TwitterCache(caffeine)
        }
      }

      import com.twitter.conversions.time._
      val twitterCache =
        Cache.create[TwitterCacheKey[Long], String](10000, 10.seconds.inSeconds)
      Await.result(
        twitterCache
          .applyIfNeeded(_ => Future.value("AAA"))(TwitterCacheKey(1))) == "AAA"
    }
  }

  "GuavaCache" should {
    "cache future value" in {
      val guava = CacheBuilder
        .newBuilder()
        .maximumSize(1)
        .expireAfterAccess(10, TimeUnit.SECONDS)
        .build[TwitterCacheKey[Long], Future[String]]
      val twitterCache = GuavaTwitterCache(guava)
      Await.result(
        twitterCache
          .applyIfNeeded(_ => Future.value("AAA"))(TwitterCacheKey(1))) == "AAA"
    }

    "with companion object" in {
      object Cache {
        def create[K <: AnyRef, V](
            maximumSize: Long,
            expireAfterAccessSeconds: Long): GuavaTwitterCache[K, V] = {
          lazy val guava = CacheBuilder
            .newBuilder()
            .maximumSize(maximumSize)
            .expireAfterAccess(expireAfterAccessSeconds, TimeUnit.SECONDS)
            .build[K, Future[V]]()
          GuavaTwitterCache(guava)
        }
      }

      import com.twitter.conversions.time._
      val twitterCache =
        Cache.create[TwitterCacheKey[Long], String](10000, 10.seconds.inSeconds)
      Await.result(
        twitterCache
          .applyIfNeeded(_ => Future.value("AAA"))(TwitterCacheKey(1))) == "AAA"
    }
  }
}
