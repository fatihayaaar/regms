package com.fayardev.regms.commentservice.service.abstracts;

import com.fayardev.regms.commentservice.dto.BaseDto;

public interface ILikeCommandHandler<T extends BaseDto> extends IHandler<T> {

    Long addLike(T like);

    boolean removeLike(T like);
}
