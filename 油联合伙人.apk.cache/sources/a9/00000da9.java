package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.common.ListUtil;
import com.facebook.stetho.common.LogUtil;
import com.facebook.stetho.common.Util;
import com.facebook.stetho.inspector.elements.Document;
import com.facebook.stetho.inspector.elements.Origin;
import com.facebook.stetho.inspector.elements.StyleAccumulator;
import com.facebook.stetho.inspector.helper.ChromePeerManager;
import com.facebook.stetho.inspector.helper.PeersRegisteredListener;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcPeer;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcResult;
import com.facebook.stetho.inspector.protocol.ChromeDevtoolsDomain;
import com.facebook.stetho.inspector.protocol.ChromeDevtoolsMethod;
import com.facebook.stetho.json.ObjectMapper;
import com.facebook.stetho.json.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class CSS implements ChromeDevtoolsDomain {
    private final Document mDocument;
    private final ObjectMapper mObjectMapper = new ObjectMapper();
    private final ChromePeerManager mPeerManager = new ChromePeerManager();

    /* loaded from: classes.dex */
    private static class PseudoIdMatches {
        @JsonProperty(required = true)
        public List<RuleMatch> matches = new ArrayList();
        @JsonProperty(required = true)
        public int pseudoId;
    }

    @ChromeDevtoolsMethod
    public void disable(JsonRpcPeer jsonRpcPeer, JSONObject jSONObject) {
    }

    @ChromeDevtoolsMethod
    public void enable(JsonRpcPeer jsonRpcPeer, JSONObject jSONObject) {
    }

    public CSS(Document document) {
        this.mDocument = (Document) Util.throwIfNull(document);
        this.mPeerManager.setListener(new PeerManagerListener());
    }

    @ChromeDevtoolsMethod
    public JsonRpcResult getComputedStyleForNode(JsonRpcPeer jsonRpcPeer, JSONObject jSONObject) {
        final GetComputedStyleForNodeRequest getComputedStyleForNodeRequest = (GetComputedStyleForNodeRequest) this.mObjectMapper.convertValue(jSONObject, GetComputedStyleForNodeRequest.class);
        final GetComputedStyleForNodeResult getComputedStyleForNodeResult = new GetComputedStyleForNodeResult();
        getComputedStyleForNodeResult.computedStyle = new ArrayList();
        this.mDocument.postAndWait(new Runnable() { // from class: com.facebook.stetho.inspector.protocol.module.CSS.1
            @Override // java.lang.Runnable
            public void run() {
                Object elementForNodeId = CSS.this.mDocument.getElementForNodeId(getComputedStyleForNodeRequest.nodeId);
                if (elementForNodeId != null) {
                    CSS.this.mDocument.getElementStyles(elementForNodeId, new StyleAccumulator() { // from class: com.facebook.stetho.inspector.protocol.module.CSS.1.1
                        @Override // com.facebook.stetho.inspector.elements.StyleAccumulator
                        public void store(String str, String str2, boolean z) {
                            if (z) {
                                return;
                            }
                            CSSComputedStyleProperty cSSComputedStyleProperty = new CSSComputedStyleProperty();
                            cSSComputedStyleProperty.name = str;
                            cSSComputedStyleProperty.value = str2;
                            getComputedStyleForNodeResult.computedStyle.add(cSSComputedStyleProperty);
                        }
                    });
                    return;
                }
                LogUtil.e("Tried to get the style of an element that does not exist, using nodeid=" + getComputedStyleForNodeRequest.nodeId);
            }
        });
        return getComputedStyleForNodeResult;
    }

    @ChromeDevtoolsMethod
    public JsonRpcResult getMatchedStylesForNode(JsonRpcPeer jsonRpcPeer, JSONObject jSONObject) {
        final GetMatchedStylesForNodeRequest getMatchedStylesForNodeRequest = (GetMatchedStylesForNodeRequest) this.mObjectMapper.convertValue(jSONObject, GetMatchedStylesForNodeRequest.class);
        GetMatchedStylesForNodeResult getMatchedStylesForNodeResult = new GetMatchedStylesForNodeResult();
        final RuleMatch ruleMatch = new RuleMatch();
        final RuleMatch accessibilityRuleMatch = getAccessibilityRuleMatch();
        getMatchedStylesForNodeResult.matchedCSSRules = ListUtil.newImmutableList(ruleMatch, accessibilityRuleMatch);
        ruleMatch.matchingSelectors = ListUtil.newImmutableList(0);
        Selector selector = new Selector();
        selector.value = "<this_element>";
        CSSRule cSSRule = new CSSRule();
        cSSRule.origin = Origin.REGULAR;
        cSSRule.selectorList = new SelectorList();
        cSSRule.selectorList.selectors = ListUtil.newImmutableList(selector);
        cSSRule.style = new CSSStyle();
        cSSRule.style.cssProperties = new ArrayList();
        ruleMatch.rule = cSSRule;
        cSSRule.style.shorthandEntries = Collections.emptyList();
        this.mDocument.postAndWait(new Runnable() { // from class: com.facebook.stetho.inspector.protocol.module.CSS.2
            @Override // java.lang.Runnable
            public void run() {
                Object elementForNodeId = CSS.this.mDocument.getElementForNodeId(getMatchedStylesForNodeRequest.nodeId);
                if (elementForNodeId != null) {
                    CSS.this.mDocument.getElementStyles(elementForNodeId, new StyleAccumulator() { // from class: com.facebook.stetho.inspector.protocol.module.CSS.2.1
                        @Override // com.facebook.stetho.inspector.elements.StyleAccumulator
                        public void store(String str, String str2, boolean z) {
                            if (z) {
                                return;
                            }
                            CSSProperty cSSProperty = new CSSProperty();
                            cSSProperty.name = str;
                            cSSProperty.value = str2;
                            ruleMatch.rule.style.cssProperties.add(cSSProperty);
                        }
                    });
                    CSS.this.mDocument.getElementAccessibilityStyles(elementForNodeId, new StyleAccumulator() { // from class: com.facebook.stetho.inspector.protocol.module.CSS.2.2
                        @Override // com.facebook.stetho.inspector.elements.StyleAccumulator
                        public void store(String str, String str2, boolean z) {
                            if (z) {
                                return;
                            }
                            CSSProperty cSSProperty = new CSSProperty();
                            cSSProperty.name = str;
                            cSSProperty.value = str2;
                            accessibilityRuleMatch.rule.style.cssProperties.add(cSSProperty);
                        }
                    });
                    return;
                }
                LogUtil.w("Failed to get style of an element that does not exist, nodeid=" + getMatchedStylesForNodeRequest.nodeId);
            }
        });
        getMatchedStylesForNodeResult.inherited = Collections.emptyList();
        getMatchedStylesForNodeResult.pseudoElements = Collections.emptyList();
        return getMatchedStylesForNodeResult;
    }

    private RuleMatch getAccessibilityRuleMatch() {
        Selector selector = new Selector();
        selector.value = "Accessibility Properties";
        CSSRule cSSRule = new CSSRule();
        cSSRule.origin = Origin.REGULAR;
        cSSRule.selectorList = new SelectorList();
        cSSRule.selectorList.selectors = ListUtil.newImmutableList(selector);
        cSSRule.style = new CSSStyle();
        cSSRule.style.cssProperties = new ArrayList();
        cSSRule.style.shorthandEntries = Collections.emptyList();
        RuleMatch ruleMatch = new RuleMatch();
        ruleMatch.matchingSelectors = ListUtil.newImmutableList(0);
        ruleMatch.rule = cSSRule;
        return ruleMatch;
    }

    /* loaded from: classes.dex */
    private final class PeerManagerListener extends PeersRegisteredListener {
        private PeerManagerListener() {
        }

        @Override // com.facebook.stetho.inspector.helper.PeersRegisteredListener
        protected synchronized void onFirstPeerRegistered() {
            CSS.this.mDocument.addRef();
        }

        @Override // com.facebook.stetho.inspector.helper.PeersRegisteredListener
        protected synchronized void onLastPeerUnregistered() {
            CSS.this.mDocument.release();
        }
    }

    /* loaded from: classes.dex */
    private static class CSSComputedStyleProperty {
        @JsonProperty(required = true)
        public String name;
        @JsonProperty(required = true)
        public String value;

        private CSSComputedStyleProperty() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class RuleMatch {
        @JsonProperty
        public List<Integer> matchingSelectors;
        @JsonProperty
        public CSSRule rule;

        private RuleMatch() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class SelectorList {
        @JsonProperty
        public List<Selector> selectors;
        @JsonProperty
        public String text;

        private SelectorList() {
        }
    }

    /* loaded from: classes.dex */
    private static class SourceRange {
        @JsonProperty(required = true)
        public int endColumn;
        @JsonProperty(required = true)
        public int endLine;
        @JsonProperty(required = true)
        public int startColumn;
        @JsonProperty(required = true)
        public int startLine;

        private SourceRange() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class Selector {
        @JsonProperty
        public SourceRange range;
        @JsonProperty(required = true)
        public String value;

        private Selector() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class CSSRule {
        @JsonProperty
        public Origin origin;
        @JsonProperty(required = true)
        public SelectorList selectorList;
        @JsonProperty
        public CSSStyle style;
        @JsonProperty
        public String styleSheetId;

        private CSSRule() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class CSSStyle {
        @JsonProperty(required = true)
        public List<CSSProperty> cssProperties;
        @JsonProperty
        public String cssText;
        @JsonProperty
        public SourceRange range;
        @JsonProperty
        public List<ShorthandEntry> shorthandEntries;
        @JsonProperty
        public String styleSheetId;

        private CSSStyle() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class ShorthandEntry {
        @JsonProperty
        public Boolean imporant;
        @JsonProperty(required = true)
        public String name;
        @JsonProperty(required = true)
        public String value;

        private ShorthandEntry() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class CSSProperty {
        @JsonProperty
        public Boolean disabled;
        @JsonProperty
        public Boolean implicit;
        @JsonProperty
        public Boolean important;
        @JsonProperty(required = true)
        public String name;
        @JsonProperty
        public Boolean parsedOk;
        @JsonProperty
        public SourceRange range;
        @JsonProperty
        public String text;
        @JsonProperty(required = true)
        public String value;

        private CSSProperty() {
        }
    }

    /* loaded from: classes.dex */
    private static class GetComputedStyleForNodeRequest {
        @JsonProperty(required = true)
        public int nodeId;

        private GetComputedStyleForNodeRequest() {
        }
    }

    /* loaded from: classes.dex */
    private static class InheritedStyleEntry {
        @JsonProperty(required = true)
        public CSSStyle inlineStyle;
        @JsonProperty(required = true)
        public List<RuleMatch> matchedCSSRules;

        private InheritedStyleEntry() {
        }
    }

    /* loaded from: classes.dex */
    private static class GetComputedStyleForNodeResult implements JsonRpcResult {
        @JsonProperty(required = true)
        public List<CSSComputedStyleProperty> computedStyle;

        private GetComputedStyleForNodeResult() {
        }
    }

    /* loaded from: classes.dex */
    private static class GetMatchedStylesForNodeRequest implements JsonRpcResult {
        @JsonProperty
        public Boolean excludeInherited;
        @JsonProperty
        public Boolean excludePseudo;
        @JsonProperty(required = true)
        public int nodeId;

        private GetMatchedStylesForNodeRequest() {
        }
    }

    /* loaded from: classes.dex */
    private static class GetMatchedStylesForNodeResult implements JsonRpcResult {
        @JsonProperty
        public List<InheritedStyleEntry> inherited;
        @JsonProperty
        public List<RuleMatch> matchedCSSRules;
        @JsonProperty
        public List<PseudoIdMatches> pseudoElements;

        private GetMatchedStylesForNodeResult() {
        }
    }
}