package com.digit.downloader.parser;

import java.io.IOException;
import java.util.List;

public interface ParserUrl {
    List<String> parse(String source) throws IOException;
}
