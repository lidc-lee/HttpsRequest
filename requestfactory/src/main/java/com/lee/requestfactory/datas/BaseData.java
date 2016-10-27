package com.lee.requestfactory.datas;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by LinJK on 6/16/16.
 *
 * Base Data Class of svc data, with an empty ID .
 */
public class BaseData implements Serializable {
   public final UUID emptyUUID= UUID.fromString("00000000-0000-0000-0000-000000000000");
}
