package codes.dreaming.emoteallowlist;

import io.wispforest.owo.config.annotation.Config;

import java.util.ArrayList;
import java.util.Set;

@Config(name = "emotecraft-allowlist", wrapperName = "EmotecraftAllowlistConfig")
public class ConfigModel {
    public Set<String> allowlist = Set.of("ebfb1e69-330a-4970-8bca-f5625c90681a");
}
