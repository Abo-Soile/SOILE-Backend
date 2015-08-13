describe("persistant data", function() {

  it("saves variable", function() {
    SOILE2.bin.savevariable("test", 100);

    var variables = SOILE2.rt.persistantDataHandler.get();
    expect(variables.test).toBe(100);
  });

  it("saves and load variable", function() {
    SOILE2.bin.savevariable("test", 10);

    var variable = SOILE2.bin.loadvariable("test");
    expect(variable).toBe(10);
  });

  it("load default value", function() {
    var variable = SOILE2.bin.loadvariable("test", 10);

    expect(variable).toBe(10);
  });
});
