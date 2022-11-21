package com.firsttrip.demo.request;

import lombok.Data;

/**
 * Search Request Class
 *
 * @author Sayem Hasnat"
 */
@Data
public class SearchRequest {
    String username;
    String password;
    String source;
    String destination;
}
