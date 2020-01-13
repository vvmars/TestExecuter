package com;

import org.springframework.beans.factory.annotation.Value;

public class Routes {
   @Value( "${amq.url}" )
   private String amq;


}
