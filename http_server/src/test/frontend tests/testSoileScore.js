describe("Scorehandler", function() {

  it("saves unnamed score", function() {
    SOILE2.bin.savescore(1000);

    var score = SOILE2.rt.scoreHandler.get();

    expect(score.score).toBe(1000);
  });

  it("saves named score", function() {
    SOILE2.bin.savescore("correct", 10);

    var score = SOILE2.rt.scoreHandler.get();

    expect(score.correct).toBe(10);
  });
});
