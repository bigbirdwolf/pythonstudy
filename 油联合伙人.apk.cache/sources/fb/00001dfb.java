package org.jsoup.select;

import java.util.Iterator;
import org.jsoup.nodes.Element;

/* loaded from: classes.dex */
abstract class StructuralEvaluator extends Evaluator {
    Evaluator evaluator;

    /* loaded from: classes.dex */
    static class Root extends Evaluator {
        @Override // org.jsoup.select.Evaluator
        public boolean matches(Element element, Element element2) {
            return element == element2;
        }
    }

    StructuralEvaluator() {
    }

    /* loaded from: classes.dex */
    static class Has extends StructuralEvaluator {
        public Has(Evaluator evaluator) {
            this.evaluator = evaluator;
        }

        @Override // org.jsoup.select.Evaluator
        public boolean matches(Element element, Element element2) {
            Iterator<Element> it = element2.getAllElements().iterator();
            while (it.hasNext()) {
                Element next = it.next();
                if (next != element2 && this.evaluator.matches(element, next)) {
                    return true;
                }
            }
            return false;
        }

        public String toString() {
            return String.format(":has(%s)", this.evaluator);
        }
    }

    /* loaded from: classes.dex */
    static class Not extends StructuralEvaluator {
        public Not(Evaluator evaluator) {
            this.evaluator = evaluator;
        }

        @Override // org.jsoup.select.Evaluator
        public boolean matches(Element element, Element element2) {
            return !this.evaluator.matches(element, element2);
        }

        public String toString() {
            return String.format(":not%s", this.evaluator);
        }
    }

    /* loaded from: classes.dex */
    static class Parent extends StructuralEvaluator {
        public Parent(Evaluator evaluator) {
            this.evaluator = evaluator;
        }

        @Override // org.jsoup.select.Evaluator
        public boolean matches(Element element, Element element2) {
            if (element == element2) {
                return false;
            }
            for (Element parent = element2.parent(); !this.evaluator.matches(element, parent); parent = parent.parent()) {
                if (parent == element) {
                    return false;
                }
            }
            return true;
        }

        public String toString() {
            return String.format(":parent%s", this.evaluator);
        }
    }

    /* loaded from: classes.dex */
    static class ImmediateParent extends StructuralEvaluator {
        public ImmediateParent(Evaluator evaluator) {
            this.evaluator = evaluator;
        }

        @Override // org.jsoup.select.Evaluator
        public boolean matches(Element element, Element element2) {
            Element parent;
            return (element == element2 || (parent = element2.parent()) == null || !this.evaluator.matches(element, parent)) ? false : true;
        }

        public String toString() {
            return String.format(":ImmediateParent%s", this.evaluator);
        }
    }

    /* loaded from: classes.dex */
    static class PreviousSibling extends StructuralEvaluator {
        public PreviousSibling(Evaluator evaluator) {
            this.evaluator = evaluator;
        }

        @Override // org.jsoup.select.Evaluator
        public boolean matches(Element element, Element element2) {
            if (element == element2) {
                return false;
            }
            for (Element previousElementSibling = element2.previousElementSibling(); previousElementSibling != null; previousElementSibling = previousElementSibling.previousElementSibling()) {
                if (this.evaluator.matches(element, previousElementSibling)) {
                    return true;
                }
            }
            return false;
        }

        public String toString() {
            return String.format(":prev*%s", this.evaluator);
        }
    }

    /* loaded from: classes.dex */
    static class ImmediatePreviousSibling extends StructuralEvaluator {
        public ImmediatePreviousSibling(Evaluator evaluator) {
            this.evaluator = evaluator;
        }

        @Override // org.jsoup.select.Evaluator
        public boolean matches(Element element, Element element2) {
            Element previousElementSibling;
            return (element == element2 || (previousElementSibling = element2.previousElementSibling()) == null || !this.evaluator.matches(element, previousElementSibling)) ? false : true;
        }

        public String toString() {
            return String.format(":prev%s", this.evaluator);
        }
    }
}