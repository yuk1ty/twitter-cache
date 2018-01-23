# twitter-cache
[![Build Status](https://travis-ci.org/yuk1ty/twitter-cache.svg?branch=master)](https://travis-ci.org/yuk1ty/twitter-cache)

This is a WIP project.

A utility library for `twitter/util/util-cache`.

## Examples

`twitter-cache` has two patterns to create cache.

1. Generate `TwitterCache` with prepared `Caffeine` object
2. Generate `TwitterCache` with companion object

These examples are below.

### Generate `TwiterCache` with prepared `Caffeine` object

To get start, all we need is create `TwitterCache` and inject `Caffeine` into it.

```scala
val caffeine = Caffeine
    .newBuilder()
    .maximumSize(1)
    .expireAfterWrite(10, TimeUnit.SECONDS)
    .build[TwitterCacheKey[Long], Future[String]]
val twitterCache = TwitterCache(caffeine)
twitterCache
    .execIfNeeded(_ => Future.value("AAA"))(TwitterCacheKey(1))
```

### Generate `TwitterCache` with companion object

Or we can create some companion object to create `Caffeine` with some properties, and inject this companion object into `TwitterCache`. It works equally well.

```scala
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
  Cache.create[TwitterCacheKey[Long], String](10000,
                                              10.seconds.inSeconds)

twitterCache.execIfNeeded(_ => Future.value("AAA"))(TwitterCacheKey(1)))
```

## Future Plan
* CacheLoader