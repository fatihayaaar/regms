package com.fayardev.regms.commentservice.service.abstracts;

import com.fayardev.regms.commentservice.dto.BaseDto;

public interface ICommentCommandHandler<T extends BaseDto> extends IHandler<T> {

    T addComment(T like);

    boolean removeComment(T like);
}
