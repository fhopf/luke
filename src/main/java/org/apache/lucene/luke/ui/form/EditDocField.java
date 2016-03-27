package org.apache.lucene.luke.ui.form;

import org.apache.lucene.index.IndexOptions;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.index.Terms;
import org.apache.lucene.index.TermsEnum;
import org.apache.lucene.luke.core.Util;
import org.apache.lucene.util.BytesRef;

import java.io.IOException;

public class EditDocField {

  public EditDocField(IndexableField f, Terms tv) throws IOException {
    this.name = f.name();
    this.boost = f.boost();
    this.stored = f.fieldType().stored();
    this.indexed = f.fieldType().indexOptions() != null;
    this.tokenized = f.fieldType().tokenized();
    this.binary = f.binaryValue() != null;
    this.hasTermVector = f.fieldType().storeTermVectors();
    this.hasTermVectorPositions = f.fieldType().storeTermVectorPositions();
    this.hasTermVectorOffsets = f.fieldType().storeTermVectorOffsets();
    this.hasTermVectorPayloads = f.fieldType().storeTermVectorPayloads();
    this.omitNorms = f.fieldType().omitNorms();
    this.indexOptions = f.fieldType().indexOptions();
    if (f.binaryValue() != null) {
      this.binary = true;
      this.storedValue = Util.bytesToHex(f.binaryValue(), true);
    } else {
      this.storedValue = f.stringValue();
    }
    if (hasTermVector && tv != null) {
      // Collect terms if the term vector available
      TermsEnum te = tv.iterator();
      BytesRef term;
      StringBuilder sb = new StringBuilder();
      while((term = te.next()) != null) {
        if (sb.length() > 0)
          sb.append(", ");
        sb.append(term.utf8ToString());

      }
      this.tokens = sb.toString();
    } else {
      this.tokens = "(TermVector for this field is not available.)";
    }
  }

  private String name;
  private float boost;
  private boolean stored;
  private boolean indexed;
  private boolean tokenized;
  private boolean binary;
  private boolean hasTermVector;
  private boolean hasTermVectorPositions;
  private boolean hasTermVectorOffsets;
  private boolean hasTermVectorPayloads;
  private boolean omitNorms;
  private IndexOptions indexOptions;
  private String storedValue;
  private String tokens;

  public String getName() {
    return name;
  }

  public float getBoost() {
    return boost;
  }

  public void setBoost(float boost) {
    this.boost = boost;
  }

  public boolean isStored() {
    return stored;
  }

  public void setStored(boolean stored) {
    stored = stored;
  }

  public boolean isIndexed() {
    return indexed;
  }

  public void setIndexed(boolean indexed) {
    this.indexed = indexed;
  }

  public boolean isTokenized() {
    return tokenized;
  }

  public void setTokenized(boolean tokenized) {
    this.tokenized = tokenized;
  }

  public boolean isBinary() {
    return binary;
  }

  public void setBinary(boolean binary) {
    this.binary = binary;
  }

  public boolean getHasTermVector() {
    return hasTermVector;
  }

  public void setHasTermVector(boolean hasTermVector) {
    this.hasTermVector = hasTermVector;
  }

  public boolean getHasTermVectorPositions() {
    return hasTermVectorPositions;
  }

  public void setHasTermVectorPositions(boolean hasTermVectorPositions) {
    this.hasTermVectorPositions = hasTermVectorPositions;
  }

  public boolean getHasTermVectorOffsets() {
    return hasTermVectorOffsets;
  }

  public void setHasTermVectorOffsets(boolean hasTermVectorOffsets) {
    this.hasTermVectorOffsets = hasTermVectorOffsets;
  }

  public boolean setHasTermVectorPayloads() {
    return hasTermVectorPayloads;
  }

  public void setHasTermVectorPayloads(boolean hasTermVectorPayloads) {
    this.hasTermVectorPayloads = hasTermVectorPayloads;
  }

  public boolean isOmitNorms() {
    return omitNorms;
  }

  public void setOmitNorms(boolean omitNorms) {
    this.omitNorms = omitNorms;
  }

  public static String[] indexOptionList = new String[] {
      "none", "docs", "docs and freqs", "docs and freqs and positions", "docs and freqs and positions and offsets"
  };

  public String getIndexOptions() {
    switch(indexOptions) {
      case NONE: return "none";
      case DOCS: return "docs";
      case DOCS_AND_FREQS: return "docs and freqs";
      case DOCS_AND_FREQS_AND_POSITIONS: return "docs and freqs and positions";
      case DOCS_AND_FREQS_AND_POSITIONS_AND_OFFSETS: return "docs and freqs and positions and offsets";
      default: return "";
    }
  }

  public void setIndexOptions(String indexOptions) {
    switch (indexOptions) {
      case "none":
        this.indexOptions = IndexOptions.NONE;
        break;
      case "docs":
        this.indexOptions = IndexOptions.DOCS;
        break;
      case "docs and freqs":
        this.indexOptions = IndexOptions.DOCS_AND_FREQS;
        break;
      case "docs and freqs and positions":
        this.indexOptions = IndexOptions.DOCS_AND_FREQS_AND_POSITIONS;
        break;
      case "docs and freqs and positions and offsets":
        this.indexOptions = IndexOptions.DOCS_AND_FREQS_AND_POSITIONS_AND_OFFSETS;
        break;
      default:
        this.indexOptions = IndexOptions.NONE;
    }
  }

  public String getStoredValue() {
    return storedValue;
  }

  public void setStoredValue(String storedValue) {
    this.storedValue = storedValue;
  }

  public String getTokens() {
    return tokens;
  }

  //public void setTokens(String tokens) {
  //  this.tokens = tokens;
  //}

}
