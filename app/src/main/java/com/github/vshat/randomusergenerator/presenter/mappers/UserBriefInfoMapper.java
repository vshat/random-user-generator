package com.github.vshat.randomusergenerator.presenter.mappers;


import com.github.vshat.randomusergenerator.model.data.UserDTO;
import com.github.vshat.randomusergenerator.presenter.vo.UserBriefInfo;
import com.github.vshat.randomusergenerator.util.TextUtils;

import java.util.ArrayList;
import java.util.List;

public class UserBriefInfoMapper {
    public static List<UserBriefInfo> map(List<UserDTO> userDTOs) {
        List<UserBriefInfo> result = new ArrayList<>(userDTOs.size());

        for (UserDTO userDTO : userDTOs) {
            String name = TextUtils.capitalizeFirstLetter(userDTO.getNameDTO().getFirst()) + " "
                    + TextUtils.capitalizeFirstLetter(userDTO.getNameDTO().getLast());
            String avatarUrl = userDTO.getPictureDTO().getLarge();

            UserBriefInfo userBriefInfo = new UserBriefInfo(name, avatarUrl);
            result.add(userBriefInfo);
        }
        return result;
    }
}
