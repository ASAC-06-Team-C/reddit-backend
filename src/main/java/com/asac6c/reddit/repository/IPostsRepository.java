package com.asac6c.reddit.repository;

import java.util.List;

public interface IPostsRepository<T, F> {

    T getPostContents(F request);

}
