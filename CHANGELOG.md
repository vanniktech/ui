# Change Log

Version 0.9.0 *(2023-12-11)*
----------------------------

- API: Kotlin Multiplatform ArgbEvaluator implementation. [\#64](https://github.com/vanniktech/ui/pull/64) ([vanniktech](https://github.com/vanniktech))
- API: Provide DurationParceler & TypeParceler annotation. [\#74](https://github.com/vanniktech/ui/pull/74) ([vanniktech](https://github.com/vanniktech))
- Behavior change: TabLayout.themeTabLayout sets background color now instead of background tint list. [\#91](https://github.com/vanniktech/ui/pull/91) ([vanniktech](https://github.com/vanniktech))
- Fix RecyclerView.scrollToBottom in case RecyclerView contains no items. [\#83](https://github.com/vanniktech/ui/pull/83) ([vanniktech](https://github.com/vanniktech))
- Technical: Configure Lint regarding new dependency versions. [\#97](https://github.com/vanniktech/ui/pull/97) ([vanniktech](https://github.com/vanniktech))
- Technical: Kotlin 1.9.21 & Target Android 34. [\#102](https://github.com/vanniktech/ui/pull/102) ([vanniktech](https://github.com/vanniktech))

Version 0.8.0 *(2023-05-14)*
----------------------------

- API: @IgnoredOnParcel common annotation. [\#58](https://github.com/vanniktech/ui/pull/58) ([vanniktech](https://github.com/vanniktech))
- API: ThemingColor is now Parcelable. [\#52](https://github.com/vanniktech/ui/pull/52) ([vanniktech](https://github.com/vanniktech))
- Breaking: Remove deprecated methods from previous release. [\#51](https://github.com/vanniktech/ui/pull/51) ([vanniktech](https://github.com/vanniktech))
- Breaking: Rename colorText to colorTextPrimary. [\#57](https://github.com/vanniktech/ui/pull/57) ([vanniktech](https://github.com/vanniktech))
- Breaking: Split colorNavigation into colorTopNavigation & colorBottomNavigation. [\#56](https://github.com/vanniktech/ui/pull/56) ([vanniktech](https://github.com/vanniktech))

Version 0.7.0 *(2023-05-01)*
----------------------------

- API: Drawable.setTint\(Color\) [\#41](https://github.com/vanniktech/ui/pull/41) ([vanniktech](https://github.com/vanniktech))
- API: EditText.cursorAtEndWithText which replaces TextView.clearAppend [\#49](https://github.com/vanniktech/ui/pull/49) ([vanniktech](https://github.com/vanniktech))
- API: MaterialToolbar.themeToolbar allows tinting navigation icon. [\#43](https://github.com/vanniktech/ui/pull/43) ([vanniktech](https://github.com/vanniktech))
- API: Support alpha in ColorPickerView & fix many bugs. [\#50](https://github.com/vanniktech/ui/pull/50) ([vanniktech](https://github.com/vanniktech))
- API: ThemingColor is now also Serializable by KotlinX. [\#42](https://github.com/vanniktech/ui/pull/42) ([vanniktech](https://github.com/vanniktech))
- Behavior Change: ColorPickerView - change stroke color for better contrast & stroke preview box. [\#44](https://github.com/vanniktech/ui/pull/44) ([vanniktech](https://github.com/vanniktech))
- Breaking: Remove deprecated methods from previous release. [\#46](https://github.com/vanniktech/ui/pull/46) ([vanniktech](https://github.com/vanniktech))
- Breaking: Require colorNavigationIcon to set when theming MaterialToolbar. [\#45](https://github.com/vanniktech/ui/pull/45) ([vanniktech](https://github.com/vanniktech))
- Bug fix: Fix parsing in Color.fromHexOrNull for 3 and 4 digit colors. [\#48](https://github.com/vanniktech/ui/pull/48) ([vanniktech](https://github.com/vanniktech))
- Bug fix: Make UiMaterialCalendarTheme & UiTimePickerTheme styles public. [\#47](https://github.com/vanniktech/ui/pull/47) ([vanniktech](https://github.com/vanniktech))

Version 0.6.0 *(2023-04-19)*
----------------------------

- API: Color - new fromArgb, fromHexOrNull, brighten & copy methods. Better toString\(\) which omits full alpha. [\#35](https://github.com/vanniktech/ui/pull/35) ([vanniktech](https://github.com/vanniktech))
- API: ColorPickerView to nicely pick a color. [\#37](https://github.com/vanniktech/ui/pull/37) ([vanniktech](https://github.com/vanniktech))
- API: MaterialColor enum gets colorToolbar. [\#34](https://github.com/vanniktech/ui/pull/34) ([vanniktech](https://github.com/vanniktech))
- API: MaterialColor enum with 400, 500 & 800 colors. [\#32](https://github.com/vanniktech/ui/pull/32) ([vanniktech](https://github.com/vanniktech))
- API: UiTheming interface which is an abstraction for a Theming to leverage custom components. [\#36](https://github.com/vanniktech/ui/pull/36) ([vanniktech](https://github.com/vanniktech))
- Technical: Use Kotlin 1.7.21 [\#33](https://github.com/vanniktech/ui/pull/33) ([vanniktech](https://github.com/vanniktech))

Version 0.5.0 *(2023-03-25)*
----------------------------

- API: Also theme RadioButton & CheckBox as a TextView. [\#5](https://github.com/vanniktech/ui/pull/5) ([vanniktech](https://github.com/vanniktech))
- API: TextView.animateText allow null text. [\#10](https://github.com/vanniktech/ui/pull/10) ([vanniktech](https://github.com/vanniktech))
- API: ViewPager.themeViewPager. [\#27](https://github.com/vanniktech/ui/pull/27) ([vanniktech](https://github.com/vanniktech))
- Behavior Change: Only append text in TextView.clearAppend when text is non-blank. [\#23](https://github.com/vanniktech/ui/pull/23) ([vanniktech](https://github.com/vanniktech))
- Dependency: Update Material Library to 1.8.0 and workaround regressions. [\#9](https://github.com/vanniktech/ui/pull/9) ([vanniktech](https://github.com/vanniktech))
- Kotlin: Use Android Source Set Layout Version 2. [\#7](https://github.com/vanniktech/ui/pull/7) ([vanniktech](https://github.com/vanniktech))
- Update dependencies and target SDK 33. [\#26](https://github.com/vanniktech/ui/pull/26) ([vanniktech](https://github.com/vanniktech))

Version 0.4.0 *(2023-01-03)*
----------------------------

- API: Split NightModeHandler into NightModeBehaviorHandler & NightModeProvider. [\#4](https://github.com/vanniktech/ui/pull/4) ([vanniktech](https://github.com/vanniktech))

Version 0.3.0 *(2022-12-30)*
----------------------------

- AndroidNightModeHandler: Improve implementation of isNightMode. [\#3](https://github.com/vanniktech/ui/pull/3) ([vanniktech](https://github.com/vanniktech))

Version 0.2.0 *(2022-12-28)*
----------------------------

- AndroidNightModeHandler: Fix isNightMode when Application Context is used. [\#2](https://github.com/vanniktech/ui/pull/2) ([vanniktech](https://github.com/vanniktech))
- API: Remove BottomSheetDialog.setNavigationBarColor instead use Window.themeWindow [\#1](https://github.com/vanniktech/ui/pull/1) ([vanniktech](https://github.com/vanniktech))

Version 0.1.0 *(2022-12-19)*
----------------------------

- Initial release
