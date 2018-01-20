package com.github.yuk1ty.twittercache

import java.util.concurrent.TimeUnit

import com.github.benmanes.caffeine.cache.Caffeine
import com.twitter.util.Future
import org.scalatest.WordSpec
import org.scalatest.concurrent.ScalaFutures

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

//  "TwitterCache" should {
//    "cache future value" in {
//      val caffeine = Caffeine
//        .newBuilder()
//        .maximumSize(1)
//        .expireAfterWrite(10, TimeUnit.SECONDS)
//        .build[TwitterCacheKey[Long], Future[String]]
//      val twitterCache = TwitterCache(caffeine)
//      twitterCache.execIfNeeded(_ => Future.value("AAA"))
//    }
//
//    "with companion objects" in {
//      object Cache {
//        def create[K, V](maximumSize: Long,
//                         expireAfterAccessSeconds: Long): TwitterCache[K, V] = {
//          lazy val caffeine = Caffeine
//            .newBuilder()
//            .maximumSize(maximumSize)
//            .expireAfterAccess(expireAfterAccessSeconds, TimeUnit.SECONDS)
//            .build[K, Future[V]]()
//          TwitterCache(caffeine)
//        }
//      }
//
//      val twitterCache = Cache.create[TwitterCacheKey[Long], String](10000, 10)
//      twitterCache.execIfNeeded(_ => Future.value(""))
//    }
//  }
}
