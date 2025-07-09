# Feature File Standards & Best Practices

## ✅ **Correct Feature File Structure:**

```
src/test/resources/features/
├── login/
│   ├── user_login.feature
│   └── forgot_password.feature
├── grievance/
│   ├── register_grievance.feature
│   ├── track_grievance.feature
│   └── update_grievance.feature
└── admin/
    ├── user_management.feature
    └── dashboard.feature
```

## ❌ **Avoid These Mistakes:**

### **1. Multiple Feature Files for Same Functionality**
```
❌ sample.feature
❌ GrievanceTest.feature  
❌ register_grievance.feature
```

### **2. Wrong Location**
```
❌ src/test/java/.../feature/  (Wrong - should be in resources)
✅ src/test/resources/features/ (Correct)
```

### **3. Generic Names**
```
❌ sample.feature
❌ test.feature
✅ register_grievance.feature
```

## ✅ **Best Practices:**

### **1. One Feature Per Business Function**
```gherkin
Feature: Grievance Registration
  # All grievance registration scenarios here
```

### **2. Descriptive Feature Names**
```
✅ register_grievance.feature
✅ track_grievance_status.feature
✅ user_authentication.feature
```

### **3. Organized by Module**
```
features/
├── authentication/
├── grievance/
├── reporting/
└── admin/
```

### **4. Clear Scenario Structure**
```gherkin
@smoke @grievance
Scenario: Register water sewage grievance
  Given I am on grievance portal
  When I register grievance with "water_issue" data
  Then grievance should be submitted successfully
```

### **5. Proper Tags Usage**
```gherkin
@smoke          # For smoke tests
@regression     # For regression tests  
@grievance      # For grievance module
@negative       # For negative scenarios
```

## **Current Status:**
- ✅ Cleaned up duplicate feature files
- ✅ Proper location: `src/test/resources/features/`
- ✅ Modular organization by functionality
- ✅ Descriptive naming convention