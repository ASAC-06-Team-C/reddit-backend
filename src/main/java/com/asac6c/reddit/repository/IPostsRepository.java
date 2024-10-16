package com.asac6c.reddit.repository;

import java.util.List;

public interface IPostsRepository<T, F> {

    List<T> getPostContents(F request);

}
