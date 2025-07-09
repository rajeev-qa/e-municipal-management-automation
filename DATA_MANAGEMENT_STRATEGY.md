# Data Management Strategy - Best Practices

## ❌ **AVOID: Dual Data Storage**
```
❌ Feature File: "Water Sewage issue"
❌ Properties File: grievance.description=Water Sewage issue
```

## ✅ **RECOMMENDED: Single Source of Truth**

### **1. Configuration vs Test Data Separation**

**Properties Files** (config/*.properties):
- Environment URLs
- Browser settings  
- Timeouts
- Application configuration

**JSON/Excel Files** (testdata/*):
- User credentials
- Test scenarios data
- Business data
- Dynamic test inputs

### **2. Data Reference Pattern**

**Feature File:**
```gherkin
Scenario: Register grievance
  When I enter grievance details from "water_issue"
  And I fill user information from "valid_user"
```

**JSON Data:**
```json
{
  "users": {
    "valid_user": {
      "name": "Suman Sarkar",
      "phone": "9155949093"
    }
  }
}
```

### **3. Data Access Hierarchy**

```
1. JSON/Excel Files (Primary test data)
   ↓
2. Properties Files (Configuration only)
   ↓
3. Feature Files (Data keys/references only)
```

### **4. Benefits of Single Source**

✅ **No Data Duplication**
✅ **Easy Maintenance** 
✅ **Data Consistency**
✅ **Environment Flexibility**
✅ **Reusable Data Sets**

### **5. Implementation Example**

```java
// Step Definition
@When("I fill user information from {string}")
public void fillUserInfo(String userKey) {
    Map<String, String> userData = JsonDataReader.getUserData(userKey);
    grievancePage.fillUserDetails(userData);
}
```

### **6. Data Management Rules**

1. **Properties** = Configuration only
2. **JSON/Excel** = Test data only  
3. **Feature Files** = Data references only
4. **One source per data type**
5. **Environment-specific configs separate**

This approach ensures maintainable, scalable, and consistent test data management.