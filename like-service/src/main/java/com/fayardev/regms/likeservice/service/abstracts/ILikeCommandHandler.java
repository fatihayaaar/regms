package com.fayardev.regms.likeservice.service.abstracts;

import com.fayardev.regms.likeservice.dto.BaseDto;

public interface ILikeCommandHandler<T extends BaseDto> extends IHandler<T> {

    Long addLike(T like);

    boolean removeLike(T like);
}
